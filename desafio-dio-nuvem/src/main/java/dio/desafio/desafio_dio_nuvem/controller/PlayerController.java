package dio.desafio.desafio_dio_nuvem.controller;


import dio.desafio.desafio_dio_nuvem.controller.dto.PlayerDto;
import dio.desafio.desafio_dio_nuvem.domain.model.Player;
import dio.desafio.desafio_dio_nuvem.service.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("players")
@Tag(name = "Players controller", description = "RESTful API for managing players")
public record PlayerController(PlayerService playerService) {

    @GetMapping
    @Operation(summary = "Get all players", description = "Retrieve a list of all players registered in the game")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful")
    })
    public ResponseEntity<List<PlayerDto>> findAll() {
        var players = playerService.findAll();
        var playersDto = players.stream().map(PlayerDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(playersDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a player by ID", description = "Retrieve a specific player by the ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful"),
            @ApiResponse(responseCode = "400", description = "Player not found")
    })
    public ResponseEntity<PlayerDto> findById(@PathVariable Long id) {
        var player = playerService.findById(id);
        return ResponseEntity.ok(new PlayerDto(player));
    }

    @GetMapping("/{username}")
    @Operation(summary = "Get a player by Username", description = "Retrieve a specific player by the username")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful"),
            @ApiResponse(responseCode = "400", description = "Player not found")
    })
    public ResponseEntity<PlayerDto> findByUsername(@PathVariable String username) {
        var player = playerService.findByUsername(username);
        return ResponseEntity.ok(new PlayerDto(player));
    }

    @PostMapping
    @Operation(summary = "Create a new player", description = "Create a new player and return the new data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Player successfully created"),
            @ApiResponse(responseCode = "422", description = "Invalid player data provided"),
    })
    public ResponseEntity<PlayerDto> crate(@RequestBody PlayerDto playerDto) {
        var player = playerService.create(playerDto.toModel());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(player.getId())
                .toUri();

        return ResponseEntity.created(location).body(new PlayerDto(player));
    }

    @PutMapping ("/{id}")
    @Operation(summary = "Update a player", description = "Update the data of an existing player based on the ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful"),
            @ApiResponse(responseCode = "400", description = "Player not found"),
            @ApiResponse(responseCode = "422", description = "Invalid player data provided"),
    })
    public ResponseEntity<PlayerDto> update(@PathVariable Long id, @RequestBody PlayerDto playerDto) {
        var player = playerService.update(id, playerDto.toModel());
        return ResponseEntity.ok(new PlayerDto(player));
    }

    @DeleteMapping ("/{id}")
    @Operation(summary = "Delete a player", description = "Delete an existing player based on the ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Player successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Player not found")
    })
    public ResponseEntity<Void> delete (@PathVariable Long id) {
        playerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

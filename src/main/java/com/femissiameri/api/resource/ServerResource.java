package com.femissiameri.api.resource;

import com.femissiameri.api.model.EStatus;
import com.femissiameri.api.model.Response;
import com.femissiameri.api.model.Server;
import com.femissiameri.api.service.ServerServiceImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static com.femissiameri.api.model.EStatus.*;
import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/servers")
public class ServerResource {

    private final ServerServiceImplementation serverService;

    @GetMapping("list")
    public ResponseEntity<Response> getServers() {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .status(OK)
                        .statusCode(OK.value())
                        .message("Servers retrieved.")
                        .data(Map.of("servers", serverService.list(30)))
                        .build()
        );
    }

    @GetMapping("/{ipAddress}/ping")
    public ResponseEntity<Response> pingServer(@PathVariable("ipAddress") String ipAddress) throws IOException {
        Server server = serverService.ping(ipAddress);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .status(OK)
                        .statusCode(OK.value())
                        .message(server.getStatus() == SERVER_UP ? "Ping success" : "Ping failed")
                        .data(Map.of("server", server))
                        .build()
        );
    }

    @PostMapping("/save")
    public ResponseEntity<Response> saveServer(@RequestBody @Valid Server server) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .message("Server created.")
                        .data(Map.of("server", serverService.create(server)))
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getServer(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .status(OK)
                        .statusCode(OK.value())
                        .message("Server retrieved.")
                        .data(Map.of("server", serverService.get(id)))
                        .build()
        );
    }

    @DeleteMapping("/{id}/delete/")
    public ResponseEntity<Response> deleteServer(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .status(OK)
                        .statusCode(OK.value())
                        .message("Server deleted.")
                        .data(Map.of("deleted", serverService.delete(id)))
                        .build()
        );
    }

    @GetMapping(value = "/images/{imageName}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getServerImage(@PathVariable("imageName") String imageName) throws IOException {
        return Files.readAllBytes(Path.of(ResourceUtils.getFile(imageName).getPath()));
    }
}

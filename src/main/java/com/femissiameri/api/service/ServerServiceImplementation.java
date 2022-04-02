package com.femissiameri.api.service;

import com.femissiameri.api.model.Server;
import com.femissiameri.api.repository.ServerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.transaction.Transactional;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Random;

import static com.femissiameri.api.model.EStatus.SERVER_DOWN;
import static com.femissiameri.api.model.EStatus.SERVER_UP;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerServiceImplementation implements ServerService {

    private final ServerRepository serverRepository;

    @Override
    public Server create(Server server) {
        log.info("Saving new server {}", server.getName());
        server.setImageUrl(setServerImageUrl());
        return serverRepository.save(server);
    }

    @Override
    public Server ping(String ipAddress) throws IOException {
        log.info("Pinging server IP: {}", ipAddress);
        Server server = serverRepository.findByIpAddress(ipAddress);
        InetAddress address = InetAddress.getByName(ipAddress);
        server.setStatus(address.isReachable(10000) ? SERVER_UP : SERVER_DOWN);
        return server;
    }

    @Override
    public Collection<Server> list(int limit) {
        log.info("Fetching all the servers");
        return serverRepository.findAll(PageRequest.of(0, limit))
                .toList();
    }

    @Override
    public Server get(Long id) {
        log.info("Fetching server by ID: {}", id);
        return serverRepository.findById(id).get();
    }

    @Override
    public Server update(Server server) {
        log.info("Updating the server {}", server.getName());
        return serverRepository.save(server);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Deleting the server ID: {}", id);
        serverRepository.deleteById(id);
        return true;
    }

    private String setServerImageUrl() {
        String[] imageNames = {
                "server1.png",
                "server2.png",
                "server3.png",
                "server4.png",
                "server5.png",
                "server6.png"
        };

        return "http://localhost:8080/api/servers/images/" + imageNames[new Random().nextInt(6)];
    }
}

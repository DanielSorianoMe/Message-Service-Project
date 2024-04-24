package com.intelligence;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/message")
public class MessageController {

    @PostMapping("/")
    public ResponseEntity<?> getMessage(@RequestBody Map<String, List<Map<String, List<String>>>> devicesData) {
        List<List<String>> messages = devicesData.get("devices").stream()
                .map(device -> device.get("message"))
                .collect(Collectors.toList());

        try {
            String reconstructedMessage = MessageReconstructor.getMessage(messages);

            if (reconstructedMessage.isEmpty()) {
                return new ResponseEntity<>("No se puede determinar el mensaje", HttpStatus.BAD_REQUEST);
            }

            Map<String, String> response = Map.of("message", reconstructedMessage);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error procesando la solicitud", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
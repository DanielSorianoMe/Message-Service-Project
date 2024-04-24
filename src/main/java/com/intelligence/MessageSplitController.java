package com.intelligence;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/message_split")
public class MessageSplitController {

    private final Map<String, List<String>> messagesByDevice = new HashMap<>();

    @PostMapping("/{device_id}")
    public ResponseEntity<?> storeMessage(@PathVariable("device_id") String deviceId, @RequestBody Map<String, List<String>> messageData) {
        List<String> message = messageData.get("message");

        if (message == null) {
            return new ResponseEntity<>("Datos de mensaje inválidos", HttpStatus.BAD_REQUEST);
        }

        messagesByDevice.put(deviceId, message);
        return new ResponseEntity<>("Mensaje almacenado", HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<?> getReconstructedMessage() {
        List<List<String>> messages = new ArrayList<>(messagesByDevice.values());

        try {
            String reconstructedMessage = MessageReconstructor.getMessage(messages);

            if (reconstructedMessage.isEmpty()) {
                return new ResponseEntity<>("No hay suficiente información para determinar el mensaje", HttpStatus.BAD_REQUEST);
            }

            Map<String, String> response = Map.of("message", reconstructedMessage);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error procesando la solicitud", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
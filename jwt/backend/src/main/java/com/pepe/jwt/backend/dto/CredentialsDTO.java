package com.pepe.jwt.backend.dto;

// Records are immutable
public record CredentialsDTO(String login, char[] password) {}

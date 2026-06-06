package com.notes.demo.domain.profile;

import java.time.LocalDateTime;

public record ProfileResponse(String username, String email, LocalDateTime createdAt) {
}

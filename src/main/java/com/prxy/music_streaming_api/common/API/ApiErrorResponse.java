package com.prxy.music_streaming_api.common.API;

import java.time.LocalDateTime;

public record ApiErrorResponse(
        int status,
        String message,
        LocalDateTime timestamp
) {
}

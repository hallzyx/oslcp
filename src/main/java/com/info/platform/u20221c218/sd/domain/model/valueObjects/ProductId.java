package com.info.platform.u20221c218.sd.domain.model.valueObjects;

import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public record ProductId(UUID productSku) {
}

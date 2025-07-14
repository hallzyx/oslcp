package com.info.platform.u20221c218.scm.domain.model.valueObjects;

import jakarta.persistence.Embeddable;

import java.util.UUID;
@Embeddable
public record ProductId(UUID productSku) {
}

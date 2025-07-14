package com.info.platform.u20221c218.sd.application.outBoundServices;

import com.info.platform.u20221c218.scm.interfaces.acl.ScmContextFacade;
import org.springframework.stereotype.Service;

@Service
public class ExternalScmService {
    private final ScmContextFacade scmContextFacade;
    public ExternalScmService(ScmContextFacade scmContextFacade) {
        this.scmContextFacade = scmContextFacade;
    }
    public Boolean existOrderInventoryByProductSku(String productSku) {
        return scmContextFacade.existOrderInventoryByProductSku(productSku);
    }

    public double calculateIfIsEnoughtAvailableQuantityWithReservedQuantity(String productSku, Double requestedQuantity) {
            var differenceQuantity = scmContextFacade.calculateReserverQuantityInventoryItem(productSku, requestedQuantity);
            return differenceQuantity;
    }

}

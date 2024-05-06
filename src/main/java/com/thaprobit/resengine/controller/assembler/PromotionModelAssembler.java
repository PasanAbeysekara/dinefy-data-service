package com.thaprobit.resengine.controller.assembler;

import com.thaprobit.resengine.controller.PromotionController;
import com.thaprobit.resengine.controller.service.HATEOASProvider;
import com.thaprobit.resengine.controller.sys.SysTagsController;
import com.thaprobit.resengine.dao.Promotion;
import com.thaprobit.resengine.dao.sys.Tags;
import com.thaprobit.resengine.facade.dto.PromotionModel;
import com.thaprobit.resengine.facade.dto.TagsModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class PromotionModelAssembler extends RepresentationModelAssemblerSupport<Promotion, PromotionModel> {

    public PromotionModelAssembler() {
        super(PromotionController.class, PromotionModel.class);
    }

    @Override
    public PromotionModel toModel(Promotion entity) {
        PromotionModel promotionModel = new PromotionModel();
        promotionModel.setPromoId(entity.getPromoId());
        promotionModel.setPropId(entity.getPropId());
        promotionModel.setDescription(entity.getDescription());
        promotionModel.setLive(entity.getLive());
        promotionModel.setMaxedOutLanding(entity.getMaxedOutLanding());
        promotionModel.setMaxedOutSearch(entity.getMaxedOutSearch());
        promotionModel.setName(entity.getName());
        promotionModel.setPromoType(entity.getPromoTypeId());
        promotionModel.setStartDate(entity.getStart());
        promotionModel.setTierId(entity.getTierId());

        promotionModel.add(HATEOASProvider.sysFacilitySelfLinkProvider(promotionModel.getPromoId().getPromoId()));

        return promotionModel;
    }

    @Override
    public CollectionModel<PromotionModel> toCollectionModel(Iterable<? extends Promotion> entities) {
        CollectionModel<PromotionModel> facilitiesModel = super.toCollectionModel(entities);

        facilitiesModel.add(HATEOASProvider.sysFacilitySelfLinkProvider(10)); // TODO change

        return facilitiesModel;
    }
}

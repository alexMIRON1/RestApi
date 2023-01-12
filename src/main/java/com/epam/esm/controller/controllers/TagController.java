package com.epam.esm.controller.controllers;

import com.epam.esm.model.entity.GiftCertificate;
import com.epam.esm.service.dto.GiftCertificateModel;
import com.epam.esm.service.dto.TagModel;
import com.epam.esm.service.logic.GiftCertificateService;
import com.epam.esm.service.logic.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/tags")
@Transactional
public class TagController {
    @Autowired
    TagService tagService;
    @Autowired
    GiftCertificateService certificateService;

    /**
     * This method is used to get tag by tag's id
     * @param id tag's id
     * @return tag
     */
    @GetMapping("/{tagId}")
    public ResponseEntity<TagModel> getById(@PathVariable("tagId") Long id){
        TagModel tagModel = tagService.getById(id);
        if(tagModel!=null){
            log.info("get tag " + tagModel.getName() + " by id " + id);
            return new ResponseEntity<>(tagModel, HttpStatus.OK);
        }
        log.error("tag model is null");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * This method is used to get all tags
     * @return list of tags
     */
    @GetMapping
    public ResponseEntity<List<TagModel>> getAll(){
        List<TagModel> tagModels = tagService.getAll();
        if(!tagModels.isEmpty()){
            log.info("get all tags " + tagModels);
            return new ResponseEntity<>(tagModels,HttpStatus.OK);
        }
        log.error("tags are null ");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * This method is used to create tag
     * @param tagModel tag
     * @return void
     */
    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody TagModel tagModel){
        if(tagModel!=null){
            tagService.insert(tagModel);
            log.info("tag model was saved " + tagModel.getName());
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        log.error("tag was null");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * This method is used to connect tag with certificate
     * @param tagModel tag
     * @param id certificate's id
     * @return void
     */
    @PostMapping("/certificates/{certId}")
    public ResponseEntity<Void> insertInCertificates(@RequestBody TagModel tagModel,
                                                     @PathVariable("certId") Long id){
        GiftCertificateModel certificateModel = certificateService.getById(id);
        if(tagModel!=null && certificateModel!=null && tagModel.getId()!=null){
            TagModel model = tagService.getById(tagModel.getId());
            certificateModel.addTag(model);
            certificateService.update(certificateModel);
            log.info("tag " + model.getName() + "was insert in certificate "  + certificateModel.getName());
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        log.error("tag model or/and certificate model is/are null ");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * This method is used to delete tag from certificate
     * @param tagId tag's id
     * @param certId gift certificate's id
     * @return void
     */
    @DeleteMapping("/{tagId}/certificates/{certId}")
    public ResponseEntity<Void> removeTagFromCertificate(@PathVariable("tagId") Long tagId,
                                                         @PathVariable("certId") Long certId){
        GiftCertificateModel certificateModel = certificateService.getById(certId);
        if(tagId!=null && certificateModel!=null){
            certificateModel.removeTag(tagId);
            certificateService.update(certificateModel);
            log.info("tag " + tagId + " was deleted from certificate " + certificateModel);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        log.error("tag model or/and certificate model is/are null ");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * This method is used to delete tag
     * @param tagId tag's id
     * @return void
     */
    @DeleteMapping("/{tagId}")
    public ResponseEntity<Void> remove(@PathVariable("tagId") Long tagId){
        TagModel tagModel = tagService.getById(tagId);
        if(tagModel!=null){
            tagService.remove(tagModel);
            log.info("tag " + tagModel + " was deleted ");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        log.error("tag is null");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * This method is used to get certificates and tags by specific tag's name
     * @param tagName tag's name
     * @return map list of certificates and list of tags
     */
    @GetMapping("/certificates")
    public ResponseEntity<Map<List<GiftCertificateModel>,List<TagModel>>> getCertificatesWithTags(
            @RequestParam(value="name")String tagName){
        Map<List<GiftCertificateModel>,List<TagModel>> map = tagService.getCertificatesWithTags(tagName);
        if(map!=null){
            log.info("get map " + map + " by specific tag's name " + tagName );
            return new ResponseEntity<>(map,HttpStatus.OK);
        }
        log.error("map is null ");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

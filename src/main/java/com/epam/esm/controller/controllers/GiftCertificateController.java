package com.epam.esm.controller.controllers;

import com.epam.esm.service.dto.GiftCertificateModel;
import com.epam.esm.service.dto.TagModel;
import com.epam.esm.service.logic.GiftCertificateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/certificates")
public class GiftCertificateController {
    @Autowired
    GiftCertificateService giftCertificateService;

    /**
     * This method is used to get gift certificates by certificate's id.
     * @param certId certificate's id
     * @return gift certificate
     */

   @GetMapping("/{certId}")
    public ResponseEntity<GiftCertificateModel> getCertificate(@PathVariable("certId") Long certId){
        GiftCertificateModel certificateModel = giftCertificateService.getById(certId);
        if(certificateModel!=null){
            log.info("get certificate " + certificateModel +" by id "  + certId);
            return new ResponseEntity<>(certificateModel, HttpStatus.OK);
        }
        log.error("certificateModel is null");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * This method is used to get all gift certificates.
     * @return list of gift certificates
     */
    @GetMapping
    public ResponseEntity<List<GiftCertificateModel>> getAll(){
        List<GiftCertificateModel> certificateModels = giftCertificateService.getAll();
        if(!certificateModels.isEmpty()){
            log.info("get all certificate " + certificateModels);
            return new ResponseEntity<>(certificateModels,HttpStatus.OK);
        }
        log.error("certificateModels is empty");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * This method is used to create gift certificate.
     * @param certificateModel gift certificate which creating
     * @return void
     */
    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody GiftCertificateModel certificateModel){
        if(certificateModel!=null){
            giftCertificateService.insert(certificateModel);
            log.info("certificate " + certificateModel.getName() +  " was inserted");
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        log.error("certificate is null");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * This method is used to delete gift certificate.
     * @param certId gift certificate's id that has to be deleted
     * @return void
     */
    @DeleteMapping("/{certId}")
    public ResponseEntity<Void> remove(@PathVariable("certId") Long certId ){
        GiftCertificateModel giftCertificateModel = giftCertificateService.getById(certId);
        if(giftCertificateModel!=null){
            giftCertificateService.remove(giftCertificateModel);
            log.info("gift certificate " + giftCertificateModel.getName() + " was deleted");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        log.error("certificate model is null");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * This method is used to update gift certificate.
     * @param name field for update
     * @param certificateModel gift certificate for update
     * @return void
     */
    @PutMapping("/{certName}")
    public ResponseEntity<Void> update(@PathVariable("certName") String name,
                                       @RequestBody GiftCertificateModel certificateModel){
        if(certificateModel!=null){
            certificateModel.setName(name);
            giftCertificateService.update(certificateModel);
            log.info("gift certificate " + certificateModel.getId() +  " was updated with name" + name);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        log.error("certificate model is null");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * This method is used to get certificates and tags by specific part of certificate's description
     * @param description certificate's description
     * @return map, key is list of gift certificates, value is list of tags
     */
    @GetMapping("/{certDescription}")
    public ResponseEntity<Map<List<GiftCertificateModel>,List<TagModel>>> getCertificatesWithTagsByPartOfDescription(
            @PathVariable("certDescription") String description){
        Map<List<GiftCertificateModel>,List<TagModel>> map = giftCertificateService
                .getCertificatesWithTagsByPartOfDescription(description);
        if(map!=null){
            log.info("get map " + map + "by part of certificate's description" + description);
            return new ResponseEntity<>(map,HttpStatus.OK);
        }
        log.debug("map is null");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/sortedASC")
    public ResponseEntity<Map<List<GiftCertificateModel>,List<TagModel>>> getCertificatesWithTagsSortByCreateDateASC(){
        Map<List<GiftCertificateModel>,List<TagModel>> map = giftCertificateService
                .getCertificatesWithTagsSortByCreateDateASC();
        if(map!=null){
            log.info("get map " + map + " sorted by created date asc" );
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        log.debug("map is null");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

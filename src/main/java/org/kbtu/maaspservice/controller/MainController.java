package org.kbtu.maaspservice.controller;

import lombok.Setter;
import org.kbtu.maaspservice.model.Point;
import org.kbtu.maaspservice.model.PointInfo;
import org.kbtu.maaspservice.model.Well;
import org.kbtu.maaspservice.repository.PointInfoRepository;
import org.kbtu.maaspservice.repository.PointRepository;
import org.kbtu.maaspservice.repository.WellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class MainController {
    @Setter(onMethod = @__(@Autowired))
    PointRepository pointRepository;

    @Setter(onMethod = @__(@Autowired))
    PointInfoRepository pointInfoRepository;

    @Setter(onMethod = @__(@Autowired))
    WellRepository wellRepository;

    @GetMapping("/{pointId}")
    public double calculateMasp(@RequestBody Well well, @PathVariable UUID pointId) {
        if (well == null) return 0;
        Point point = pointRepository.findById(pointId).orElse(null);
        if (point == null) return 0;
        double maasp = switch(point.getCases()) {
            case "2" -> switch(point.getItem()) {
                case "Liner element rating" -> (well.getDFORM() * well.getFSFORM()) + well.getPKR() - (well.getDPP() * well.getGA());
                case "Liner hanger packer burst" -> well.getPBLH() - (well.getDLH() * (well.getGA() - well.getBFB()));
                case "Formation strength" -> well.getDSH() * (well.getFSA() - well.getGA());
                default -> throw new IllegalArgumentException("Please enter valid item point");
            };
            default -> switch(point.getItem()) {
                case "Safety valve collapse" -> well.getPCSV() - (well.getDSV() * (well.getGA() - well.getGTBG()));
                case "Accessory collapse" -> well.getPCACC() - (well.getDACC() * (well.getGA() - well.getGTBG()));
                case "Packer collapse" -> well.getPCPP() - (well.getDPP() * (well.getGA() - well.getGTBG()));
                case "Packer element rating" -> (well.getDFORM() * well.getFSFORM()) + well.getPKR() - (well.getDPP() * well.getGA());
                case "Tubing collapse" -> well.getPCTBG() - (well.getDPP() * (well.getGA() - well.getGTBG()));
                default -> throw new IllegalArgumentException("Please enter valid item point");
            };
        };
        pointInfoRepository.save(new PointInfo(point, well, maasp));
        return maasp;
    }

    @GetMapping("/history/{id}")
    public double getMaaspHistory(@PathVariable UUID id) {
        PointInfo pointInfo = pointInfoRepository.findById(id).orElse(null);
        return pointInfo != null ? pointInfo.getMaasp() : 0;
    }

    @PostMapping("/wells")
    public UUID registerWell(@RequestBody Well well) {
        wellRepository.save(well);
        return well.getId();
    }

    @GetMapping("/wells")
    public List<Well> getAllWells() {
        return wellRepository.findAll();
    }

    @GetMapping("/points")
    public List<Point> getAllPoints() {
        return pointRepository.findAll();
    }

    @PostMapping("/points")
    public UUID registerPoint(@RequestBody Point point) {
        pointRepository.save(point);
        return point.getId();
    }
}

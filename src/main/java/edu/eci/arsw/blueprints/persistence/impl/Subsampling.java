package edu.eci.arsw.blueprints.persistence.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.Filter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("SubsamplingFilter")
public class Subsampling implements Filter{

    @Override
    public Blueprint applyFilter(Blueprint blueprint) {
        List<Point> original = blueprint.getPoints();
        List<Point> newPoints = new ArrayList<>();
        for(int i = 0; i < original.size(); i += 2){
            newPoints.add(original.get(i));
        }
        blueprint.setPoints(newPoints);
        return blueprint;
    }

    @Override
    public Set<Blueprint> multiFilterBlueprint(Set<Blueprint> blueprints) {
        for (Blueprint blueprint : blueprints) {
            applyFilter(blueprint);
        }
        return blueprints;
    }
}

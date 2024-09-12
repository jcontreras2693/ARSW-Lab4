package edu.eci.arsw.blueprints.persistence.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.Filter;


@Service
@Qualifier("RedundancyFilter")
public class Redundancy implements Filter {

    @Override
    public Blueprint applyFilter(Blueprint blueprint) {
        List<Point> original = blueprint.getPoints();
        List<Point> repeated = new ArrayList<>();
        for (int i = 0; i < original.size(); i++) {
            for (int j = i + 1; j < original.size(); j++) {
                if (areEquals(original.get(i), original.get(j))) {
                    repeated.add(original.get(i));
                    break;
                }
            }
        }
        blueprint.setPoints(removeRepeatedPoints(repeated, original));
        return blueprint;
    }

    @Override
    public Set<Blueprint> multiFilterBlueprint(Set<Blueprint> blueprints) {
        for (Blueprint i : blueprints) {
            applyFilter(i);
        }
        return blueprints;
    }

    public boolean areEquals(Point p1, Point p2) {
        return (p1.getX() == p2.getX() && p1.getY() == p2.getY());
    }

    public List<Point> removeRepeatedPoints(List<Point> repeatedPoints, List<Point> ptsAll) {
        List<Point> x = new ArrayList<>(ptsAll);
        for (Point i: repeatedPoints) {
            x.remove(i);
        }
        return x;
    }

}

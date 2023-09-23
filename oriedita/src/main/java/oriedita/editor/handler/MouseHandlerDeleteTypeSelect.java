package oriedita.editor.handler;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import oriedita.editor.canvas.MouseMode;
import oriedita.editor.databinding.ApplicationModel;
import origami.Epsilon;
import origami.crease_pattern.element.LineSegment;
import origami.crease_pattern.element.Point;

@ApplicationScoped
@Handles(MouseMode.DELETE_LINE_TYPE_SELECT_73)
public class MouseHandlerDeleteTypeSelect extends BaseMouseHandlerBoxSelect {

    private ApplicationModel applicationModel;

    @Inject
    public MouseHandlerDeleteTypeSelect(ApplicationModel applicationModel) {
        this.applicationModel = applicationModel;
    }

    public void mouseReleased(Point p0){
        super.mouseReleased(p0);
        d.getLineStep().clear();

        Point p = new Point();
        p.set(d.getCamera().TV2object(p0));

        int del = applicationModel.getDelLineType();

        if (selectionStart.distance(p0) > Epsilon.UNKNOWN_1EN6) {//現状では赤を赤に変えたときもUNDO用に記録されてしまう20161218
            if (d.insideToDelete(selectionStart, p0, del)) {
                d.record();
            }
        } else {//現状では赤を赤に変えたときもUNDO用に記録されてしまう20161218
            if (d.getFoldLineSet().closestLineSegmentDistance(p) < d.getSelectionDistance()) {//点pに最も近い線分の番号での、その距離を返す	public double closestLineSegmentDistance(Ten p)
                LineSegment s = d.getFoldLineSet().closestLineSegmentSearch(p);

                switch(del){
                    case -1:
                        d.getFoldLineSet().deleteLine(s);
                        d.record();
                        break;
                    case 0:
                        if(s.getColor().getNumber() == 0){
                            d.getFoldLineSet().deleteLine(s);
                            d.record();
                        }
                        break;
                    case 1:
                        if(s.getColor().getNumber() == 1 || s.getColor().getNumber() == 2){
                            d.getFoldLineSet().deleteLine(s);
                            d.record();
                        }
                        break;
                    case 2:
                    case 3:
                    case 4:
                        if (s.getColor().getNumber() == del - 1){
                            d.getFoldLineSet().deleteLine(s);
                            d.record();
                        }
                        break;
                    default:
                        break;
                }
            }
        }
    }

}

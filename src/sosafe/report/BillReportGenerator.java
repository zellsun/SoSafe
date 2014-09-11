/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sosafe.report;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import sosafe.control.ViewController;
import sosafe.model.SensorInfo;
import sosafe.report.htmlhelper.HTMLTagComposite;

/**
 *
 * @author Z
 */
public class BillReportGenerator {
    private final HTMLFormatter formatter;

    public BillReportGenerator(HTMLFormatter formatter) {
        this.formatter = formatter;
    }

    public void buildBillReport() {
        HTMLTagComposite baseContext = formatter.getCurrentContext();
        
        HTMLTagComposite tableContext = formatter.startNewSection(baseContext, SimpleHTMLFormatter.TAG_TABLE, SimpleHTMLFormatter.ATTRIBUTE_DEFAULT_TABLE, "");
        HTMLTagComposite captionContext = formatter.startNewSection(tableContext, SimpleHTMLFormatter.TAG_TABLE_CAPTION);
        formatter.writeInSection(captionContext, SimpleHTMLFormatter.TAG_HEADING_LEVEL_2, "Bill Report");
        
        HTMLTagComposite rowContext = formatter.startNewSection(tableContext, SimpleHTMLFormatter.TAG_TABLE_ROW);
        formatter.writeInSection(rowContext, SimpleHTMLFormatter.TAG_TABLE_HEADING, "Sensor ID");
        formatter.writeInSection(rowContext, SimpleHTMLFormatter.TAG_TABLE_HEADING, "Location");
        formatter.writeInSection(rowContext, SimpleHTMLFormatter.TAG_TABLE_HEADING, "Price");
        
        float totalPrice = 0;
        List<SensorInfo> sensorList = ViewController.getInstance().getSensorInfoJpaController().findSensorInfoEntities();
        Collections.sort(sensorList, new Comparator<SensorInfo>() {
            @Override
            public int compare(sosafe.model.SensorInfo o1, sosafe.model.SensorInfo o2) {
                if (o1.getSensorId() > o2.getSensorId()) {
                    return 1;
                } else if (o1.getSensorId() < o2.getSensorId()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        for (SensorInfo sensor : sensorList) {
            rowContext = formatter.startNewSection(tableContext, SimpleHTMLFormatter.TAG_TABLE_ROW);
            formatter.writeInSection(rowContext, SimpleHTMLFormatter.TAG_TABLE_DATA, String.valueOf(sensor.getSensorId()));
            formatter.writeInSection(rowContext, SimpleHTMLFormatter.TAG_TABLE_DATA, sensor.getSensorLocation());
            formatter.writeInSection(rowContext, SimpleHTMLFormatter.TAG_TABLE_DATA, String.valueOf(sensor.getSensorPrice()));
            totalPrice += sensor.getSensorPrice();
        }
        
        HTMLTagComposite priceContext = formatter.startNewSection(baseContext, SimpleHTMLFormatter.TAG_HEADING_LEVEL_3, "Your Total Price is: ");
        formatter.writeInSection(priceContext, String.format("%.2f", totalPrice));
    }
}

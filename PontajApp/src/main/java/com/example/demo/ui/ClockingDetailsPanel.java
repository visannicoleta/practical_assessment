package com.example.demo.ui;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import com.example.demo.classes.ClockingDetails;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class ClockingDetailsPanel extends HorizontalLayout {

	public ClockingDetailsPanel(ClockingDetails clockingDetails) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(clockingDetails.getCurrentDate());

		Label day = new Label("<td>" + String.valueOf(cal.get(Calendar.DAY_OF_MONTH)) + "</td>", ContentMode.HTML);
		Label dayName = new Label(
				"<td>" + new SimpleDateFormat("EEEE", Locale.ENGLISH).format(clockingDetails.getCurrentDate().getTime())
						+ "</td>",
				ContentMode.HTML);

		Label start = new Label(clockingDetails.getStart());
		Label stop = new Label(clockingDetails.getStop());

		addComponent(day);
		addComponent(dayName);
		addComponent(start);
		addComponent(stop);

	}

}

package com.example.demo.ui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import com.example.demo.classes.ClockingDetails;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class ClockingDetailsPanel extends GridLayout {

	public ClockingDetailsPanel(ArrayList<ClockingDetails> clockingDetails) {
		this.setColumns(5);
		this.setRows(clockingDetails.size()+1);
		this.setWidth("400px");

		for (int i = 0; i < clockingDetails.size(); i++) {
			for (int j = 0; j < 4; j++) {
				Component genericComponent = null;
				
//				if (i == 0 && j == 0) {
//					genericComponent = new Label("Day");
//				} else if (i == 0 && j == 1) {
//					genericComponent = new Label("Day name");
//				} else if (i == 0 && j == 2) {
//					genericComponent = new Label("Start");
//				} else if (i == 0 && j == 3) {
//					genericComponent = new Label("Leave");
//				} else 
					if (j == 0) {
					Calendar cal = Calendar.getInstance();
					cal.setTime(clockingDetails.get(i).getCurrentDate());

					genericComponent = new Label("<td>" + String.valueOf(cal.get(Calendar.DAY_OF_MONTH)) + "</td>",
							ContentMode.HTML);
				} else if (j == 1) {
					genericComponent = new Label(
							"<td>" + new SimpleDateFormat("EEEE", Locale.ENGLISH)
									.format(clockingDetails.get(i).getCurrentDate().getTime()) + "</td>",
							ContentMode.HTML);

				} else if (j == 2) {
					genericComponent = new Label(clockingDetails.get(i).getStart());
				} else {
					genericComponent = new Label(clockingDetails.get(i).getStop());
				}
				addComponent(genericComponent, j, i);
				this.setComponentAlignment(genericComponent, Alignment.MIDDLE_CENTER);
			}

		}

	}

}

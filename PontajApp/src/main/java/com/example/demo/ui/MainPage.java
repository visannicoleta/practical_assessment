package com.example.demo.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.classes.ClockingDetails;
import com.example.demo.classes.ClockingUtil;
import com.example.demo.repository.ClockingService;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PopupView;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;


@SpringUI
public class MainPage extends UI {

	@Autowired
	ClockingService clockingService;
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy");

	@Override
	protected void init(VaadinRequest request) {
		VerticalLayout mainLayout = new VerticalLayout();
		GridLayout grid = new GridLayout();
		
		/**
		 * popup - add clocking details
		 */

		VerticalLayout content = new VerticalLayout();
		DateField currentDate = new DateField();
		TextField start = new TextField("Start hour");
		TextField stop = new TextField("Leave hour");

		PopupView popupView = new PopupView(null, content);

		Button saveDetails = new Button("Save", click -> {
			Calendar cal = Calendar.getInstance();
			cal.setTime(java.sql.Date.valueOf(currentDate.getValue()));

			if (currentDate.isEmpty() || start.isEmpty() || stop.isEmpty()) {
				Notification.show("Please fill in all fields !");

			} else if (!start.getValue().contains(":") || !stop.getValue().contains(":")) {

				Notification.show("Start and Leave hours must be in this format: 'HH:MM'");

			} else if (cal.get(Calendar.DAY_OF_WEEK) == 1 || cal.get(Calendar.DAY_OF_WEEK) == 7) {

				Notification.show("Weekend days are not allowed !");
			} else {
				clockingService.saveClockingDetails(java.sql.Date.valueOf(currentDate.getValue()), start.getValue(),
						stop.getValue());
				popupView.setPopupVisible(false);
			}

		});

		content.addComponent(currentDate);
		content.addComponent(start);
		content.addComponent(stop);
		content.addComponent(saveDetails);
		
		Button btnClockingDetails = new Button("Add clocking details", click -> {
			popupView.setPopupVisible(true);
		});

		mainLayout.addComponent(popupView);
		mainLayout.setComponentAlignment(popupView, Alignment.MIDDLE_CENTER);

		/*
		 * display available months: last and current ones
		 */

		List<ClockingDetails> clockingDetailsList = new ArrayList<>();

		List<String> monthsToDisplay = new ArrayList<String>();
		monthsToDisplay = ClockingUtil.getMonthsToDisplay();
		ListSelect<String> listSelect = new ListSelect<>("Select a month", monthsToDisplay);
		listSelect.setRows(3);
		listSelect.select(monthsToDisplay.get(0));
		StringBuilder sb = new StringBuilder();
		sb.append("01-");
		sb.append(monthsToDisplay.get(0));
		try {
			Date date = dateFormat.parse(sb.toString());
			clockingDetailsList = clockingService.findAllClockingDetails();
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		listSelect.setWidth(50.0f, Unit.PERCENTAGE);
		String var = monthsToDisplay.get(0);
		String var2 = monthsToDisplay.get(1);

		// listSelect.addValueChangeListener(
		// event -> {
		// if(listSelect.getValue().equals(var)){
		// //clockingDetailsList = clockingService.findByMonth(new Date());
		//
		// }
		// else{
		//
		// }
		//
		// });

		/**
		 * grid
		 */

		Label day = new Label("<th>" + "Day" + "</th>", ContentMode.HTML);
		Label dayName = new Label("<th>" + "Day name" + "</th>", ContentMode.HTML);

		Label startHeader = new Label("<th>" + "Start" + "</th>", ContentMode.HTML);
		Label stopHeader = new Label("<th>" + "Leave" + "</th>", ContentMode.HTML);
		HorizontalLayout hLayout = new HorizontalLayout();

		hLayout.addComponent(day);
		hLayout.addComponent(dayName);
		hLayout.addComponent(startHeader);
		hLayout.addComponent(stopHeader);
		hLayout.setWidth("400px");
		
		ClockingDetailsPanel grid2 = new ClockingDetailsPanel((ArrayList<ClockingDetails>) clockingDetailsList);

		HorizontalLayout horiz = new HorizontalLayout();
		Label lblWorkTime = new Label("Work time:");
		TextField workTime = new TextField();
		workTime.setValue(ClockingUtil.workTime(clockingDetailsList));
		workTime.isReadOnly();
		workTime.setWidth("80px");
		horiz.addComponent(lblWorkTime);
		horiz.addComponent(workTime);
		horiz.setWidth("400px");

		mainLayout.addComponent(listSelect);
		mainLayout.addComponent(hLayout);
		mainLayout.addComponent(grid2);
		mainLayout.addComponent(horiz);
		mainLayout.addComponent(btnClockingDetails);
		setContent(mainLayout);

	}

}

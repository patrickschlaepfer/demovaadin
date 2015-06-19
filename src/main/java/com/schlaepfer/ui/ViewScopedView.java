package com.schlaepfer.ui;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.schlaepfer.entity.BinaryData;
import com.schlaepfer.repo.BinaryDataRepository;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

@SpringView(name = ViewScopedView.VIEW_NAME)
public class ViewScopedView extends VerticalLayout implements View {
	
	private static final long serialVersionUID = 1L;
	
	public static final String VIEW_NAME = "view";
	
	// a new instance will be created for every view instance
    @Autowired
    private ViewGreeter viewGreeter;

    // the same instance will be used by all views of the UI
    @Autowired
    private Greeter uiGreeter;
    
    @Autowired
    private BinaryDataRepository binaryDataRepository;

	@PostConstruct
	void init() {
		setMargin(true);
		setSpacing(true);
		addComponent(new Label("This is a view scoped view"));
		addComponent(new Label(uiGreeter.sayHello()));
        addComponent(new Label(viewGreeter.sayHello()));
        
        BeanItemContainer<BinaryData> container =
        		new BeanItemContainer<BinaryData>(BinaryData.class);
        for(BinaryData binaryData : binaryDataRepository.findAll()) {
        	container.addBean(binaryData);
        }
        
       	final Table table = new Table("Binary Data");
       	table.setContainerDataSource(container);
        
       	addComponent(table);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// the view is constructed in the init() method()
	}
}

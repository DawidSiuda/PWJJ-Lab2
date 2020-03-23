package application;

import java.io.File;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import package1.FloatElement;
import package1.FloatSorterQuick;
import package1.IElement;
import package1.IntElement;
import package1.IntSorter;

public class Controler {
    //
    // FX variables
    //

    public TextField stringTextPool;
    public TextField valueTextPool;
    public TextField numberToGenerateTextPool;

    public ImageView background;

    public ChoiceBox<String> languageChoiceBox;
    public ChoiceBox<AlgorithmsEnum> algorithmChoiceBox;

    public Button ButtonAdd;
    public Button ButtonChangeLanguage;
    public Button ButtonGenerateFloat;
    public Button ButtonGenerateInt;
    public Button ButtonRunAlgorithm;

    public Label LabelNumberOfRecord;
    public Label LabelAddRecord;
    public Label LabelDate;
    public Label LabelDateText;
    public Label LabelSelectAlgorithm;
    public Label LabelSelectLanguage;
    public Label LabelString;
    public Label LabelValue;

    public Menu MenuFile;
    public Menu MenuHelp;

    public MenuItem MenuCloce;
    public MenuItem MenuAbout;

    //
    // Local variables.
    //

    ResourceBundle bundle;

    private List<FloatElement> varList;

    ObservableList<String> languagesStringList = FXCollections.observableArrayList("PL", "EN(GB)", "EN(USA)");

    public enum AlgorithmsEnum {
        QUICK_IELE("QuickSort IElement"),
        QUICK_INTELE("QuickSort IntElement");

        private String label;

        AlgorithmsEnum(String label) {
            this.label = label;
        }

        public String toString() {
            return label;
        }
    }

    public ListView<String> listView;

    private Locale locale;

    /////////////////////////////////////////////////////////////////////////////////////////////
    // Methods
    //

    /**
     * Constructor
     */
    public Controler() {
        varList = new ArrayList<FloatElement>();
    }

    /**
     * Initialize FX controllers.
     */
    public void initialize() {

        //
        // Language ChoiceBox
        //
        System.out.println("FUNCTION: initialize");

        languageChoiceBox.setItems(languagesStringList);

        locale = new Locale("PL");
        bundle = ResourceBundle.getBundle("ApplicationResources", locale);

        languageChoiceBox.setValue("PL");

        setLanguage();

        //
        // Algorithm ChoiceBox
        //

        algorithmChoiceBox.setItems(FXCollections.observableArrayList(AlgorithmsEnum.values()));
        algorithmChoiceBox.setValue(AlgorithmsEnum.QUICK_IELE);

        //
        // Set default value of number of record to generate.
        //

        numberToGenerateTextPool.setText("25");
    }

    public void addButtonClicked() {

        System.out.println("FUNCTION: addButtonClicked");

        String strInput = stringTextPool.getText();

        Float value;

        try	{
            value = Float.parseFloat(valueTextPool.getText());
        }
        catch(Exception e) {
            MyMessage.show(bundle.getString("MessageIncorrectValue"));
            return;
        }

        System.out.println("Adding " + value + " " + strInput);
        varList.add(new FloatElement(value, strInput));

        reloadListView();
    }

    public void generateIntButtonClicked()	{
        Integer value;

        try	{
            value = Integer.parseInt(numberToGenerateTextPool.getText());
        }
        catch(Exception e) {
        	MyMessage.show(bundle.getString("MessageIncorrectValue"));
            return;
        }

        varList.clear();
        Random random = new Random();

        for (int i = 0; i< value; i++)
        {
            varList.add(new FloatElement((float)random.nextInt(100000), ""));
        }

        reloadListView();
    }

    public void generateFloatButtonClicked() {
        Integer value;

        try	{
            value = Integer.parseInt(numberToGenerateTextPool.getText());
        }
        catch(Exception e) {
        	MyMessage.show(bundle.getString("MessageIncorrectValue"));
            return;
        }

        varList.clear();
        Random random = new Random();

        for (int i = 0; i< value; i++)
        {
        	float tempValue = (float)random.nextInt(1000000)/10;
            varList.add(new FloatElement(tempValue, ""));
        }

        reloadListView();
    }

    public void runAlgorithmButtonClicked() {

        switch (algorithmChoiceBox.getSelectionModel().getSelectedItem())
        {
	        case QUICK_IELE:
	        	{
		    		FloatSorterQuick floatSorterQuick = new FloatSorterQuick();

		    		List<IElement> tempElementList = new ArrayList<IElement>();

		    		for(IElement flEle : varList)
		    		{
//		    			float value = Math.round(flEle.getValue());
//		    			String name = flEle.getName();
		    			tempElementList.add(flEle);
		    		}

		    		tempElementList = floatSorterQuick.solve2(tempElementList);

		    		varList.clear();

		    		for(IElement iEle : tempElementList)
		    		{
		    			float value = (float)iEle.getValue();
		    			String name = iEle.getName();
		    			varList.add(new FloatElement(value, name));
		    		}

		    		break;
	//				FloatSorterQuick fs = new FloatSorterQuick();
	//
	//				List<IElement<Float>> tempIElementList = new ArrayList<IElement<Float>>();
	//
	//				for(FloatElement flEle : varList)
	//				{
	//					tempIElementList.add(flEle);
	//				}
	//
	//				tempIElementList = fs.solve2(tempIElementList);
	//
	//				varList.clear();
	//
	//				for(IElement flEle : tempIElementList)
	//				{
	//					varList.add((FloatElement)flEle);
	//				}
	         //       break;
	            }
	        case QUICK_INTELE:
	        {
	    		IntSorter intSorter = new IntSorter();

	    		List<IntElement> tempElementList = new ArrayList<IntElement>();

	    		for(FloatElement flEle : varList)
	    		{
	    			int value = Math.round(flEle.getValue());
	    			String name = flEle.getName();
	    			tempElementList.add(new IntElement(value, name));
	    		}

	    		tempElementList = intSorter.solve(tempElementList);

	    		varList.clear();

	    		for(IntElement intEle : tempElementList)
	    		{
	    			float value = (float)intEle.getValue();
	    			String name = intEle.getName();
	    			varList.add(new FloatElement(value, name));
	    		}

	    		break;
	        }
        }

		reloadListView();
    }

    public void closeButtonClicked(){
        System.exit(1);
    }

    public void aboutButtonClicked(){
        MyMessage.show(bundle.getString("MessageAbout"));
    }

    public void buttonChangeLanguageClicked() {

    	System.out.println("FUNCTION: buttonChangeLanguageClicked");

    	switch (languageChoiceBox.getSelectionModel().getSelectedItem())
    	{
		    case "PL":
		    {
		    	locale = new Locale("PL");
		    	bundle = ResourceBundle.getBundle("ApplicationResources", locale);
		    	break;
		    }
		    case "EN(GB)":
		    {
		    	locale = Locale.UK;
		    	bundle = ResourceBundle.getBundle("ApplicationResources", locale);
		    	break;
		    }
		    case "EN(USA)":
		    {
		    	locale = Locale.US;
		    	bundle = ResourceBundle.getBundle("ApplicationResources", locale);
		    	break;
		    }
    	}

    	setLanguage();
    }

    private void setLanguage()	{
        System.out.println("FUNCTION: setLanguage");
        System.out.println("new language: " + bundle.getLocale().getDisplayName());

        ButtonAdd.setText(bundle.getString("ButtonAdd"));
        ButtonChangeLanguage.setText(bundle.getString("ButtonChangeLanguage"));
        ButtonGenerateFloat.setText(bundle.getString("ButtonGenerateFloat"));
        ButtonGenerateInt.setText(bundle.getString("ButtonGenerateInt"));
        ButtonRunAlgorithm.setText(bundle.getString("ButtonRunAlgorithm"));

        LabelNumberOfRecord.setText(bundle.getString("LabelNumberOfRecordToGenerate"));
        LabelAddRecord.setText(bundle.getString("LabelAddRecord"));
        LabelDate.setText(bundle.getString("LabelDate"));
        // TO DO //
        LabelSelectAlgorithm.setText(bundle.getString("LabelSelectAlgorithm"));
        LabelSelectLanguage.setText(bundle.getString("LabelSelectLanguage"));
        LabelString.setText(bundle.getString("LabelString"));
        LabelValue.setText(bundle.getString("LabelValue"));

        MenuFile.setText(bundle.getString("MenuFile"));
        MenuHelp.setText(bundle.getString("MenuHelp"));

        MenuCloce.setText(bundle.getString("MenuCloce"));
        MenuAbout.setText(bundle.getString("MenuAbout"));

        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, locale);
        LabelDateText.setText(dateFormat.format(new Date()));
    }

    private void reloadListView()
    {
        listView.getItems().clear();

        for (IElement<Float> var : varList)
        {
            String tmp = var.getValue() + " " + var.getName();
            listView.getItems().add(tmp);
        }
    }


}

package khai.detely.components;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;

public class NumberTextField extends TextField {

    private final NumberFormat nf;
    private ObjectProperty<BigDecimal> number = new SimpleObjectProperty<>();

    public final BigDecimal getNumber() {
        return number.get();
    }

    public final double getNumberAsDouble() {
        return number.get().doubleValue();
    }

    public final void setNumber(BigDecimal value) {
        number.set(value);
    }

    public ObjectProperty<BigDecimal> numberProperty() {
        return number;
    }

    public NumberTextField() {
        this(BigDecimal.ZERO);
    }

    public NumberTextField(BigDecimal value) {
        this(value, NumberFormat.getInstance());
        initHandlers();
    }

    public NumberTextField(BigDecimal value, NumberFormat nf) {
        super();
        this.nf = nf;
        nf.setParseIntegerOnly(true);
        initHandlers();
        setNumber(value);
    }

    private void initHandlers() {

        // try to parse when focus is lost or RETURN is hit
        setOnAction(arg0 -> parseAndFormatInput());

        focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.booleanValue()) {
                parseAndFormatInput();
            }
        });

        // Set text in field if BigDecimal property is changed from outside.
        numberProperty().addListener((obserable, oldValue, newValue) -> setText(nf.format(newValue)));
    }

    /**
     * Tries to parse the user input to a number according to the provided
     * NumberFormat
     */
    private void parseAndFormatInput() {
        try {
            String input = getText();
            if (input == null || input.length() == 0 || !input.matches("[0-9.-]*")) {
                throw new ParseException("", 0);
            }
            BigDecimal newValue = new BigDecimal(input);
            if (newValue.doubleValue() < 0 || !isValidValue(newValue.doubleValue())) {
                throw new ParseException("", 0);
            }
            setNumber(newValue);
            selectAll();
        } catch (ParseException ex) {
            // If parsing fails keep old number
            setText(nf.format(number.get()));
        }
    }

    private boolean isValidValue(double value) {
        switch (getId()) {
            case "windowWidth":
            case "capacity":
                return value >= 1;
            case "acknowledgmentProbability":
            case "transmissionProbability":
                return value <= 1 && value >= 0;
            default:
                return true;
        }
    }
}
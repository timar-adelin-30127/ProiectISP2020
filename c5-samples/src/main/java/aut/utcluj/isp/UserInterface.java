package aut.utcluj.isp;
import aut.utcluj.isp.ex4.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class UserInterface {

    AirplaneTicketController ticketController = new AirplaneTicketController();

    public JPanel panelMain;
    private JLabel labelManagerTicketId;
    private JTextField textFieldManagerTicketId;
    private JLabel labelManagerDestination;
    private JLabel labelManagerPrice;
    private JLabel labelManagerStatus;
    private JLabel labelManagerCustomerId;
    private JTextField textFieldManagerDestination;
    private JTextField textFieldManagerStatus;
    private JTextField textFieldManagerCustomerId;
    private JTextField textFieldManagerPrice;
    private JTextField textFieldManagerGet;
    private JButton buttonGet;
    private JButton buttonCancel;
    private JButton buttonModify;
    private JTextField textFieldStoreDestination;
    private JTextField textFieldStoreCustomerId;
    private JButton buttonStoreBuy;
    private JLabel labelStoreDestination;
    private JLabel labelStoreCustomerId;
    private JLabel labelErrorMessage;
    private JTextArea textAreaView;
    private JComboBox comboBoxViewFilter;
    private JButton buttonViewFilter;
    private JButton groupViewButton;
    private JLabel labelViewFilter;
    private JLabel labelViewGroup;
    private JTextField textFieldStoreError;

    public UserInterface()
    {
        comboBoxViewFilter.addItem("NOT SELECTED");
        comboBoxViewFilter.addItem(TicketStatus.NEW);
        comboBoxViewFilter.addItem(TicketStatus.ACTIVE);
        comboBoxViewFilter.addItem(TicketStatus.CANCELED);

        List<AirplaneTicket> atList = ticketController.getTickets();

        for ( AirplaneTicket at : atList ) {
            textAreaView.append(at.getId() + ",");
            textAreaView.append(at.getDestination() + ",");
            textAreaView.append(at.getPrice().toString() + ",");
            textAreaView.append(at.getStatus().toString() + ",");
            textAreaView.append(at.getCustomerId() + ";");
            textAreaView.append("\n");
        }


        buttonGet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    AirplaneTicket at = ticketController.getTicketDetails(textFieldManagerGet.getText());
                    textFieldManagerTicketId.setText(at.getId());
                    textFieldManagerCustomerId.setText(at.getCustomerId());
                    textFieldManagerDestination.setText(at.getDestination());
                    textFieldManagerPrice.setText(at.getPrice().toString());
                    textFieldManagerStatus.setText(at.getStatus().toString());
                    textFieldManagerGet.setText("");
                } catch (NoTicketAvailableException ex) {
                    textFieldManagerGet.setText("Ticket not found");
                }

            }
        });
        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ticketController.cancelTicket(textFieldManagerTicketId.getText());
                    textFieldManagerGet.setText("");
                } catch (NoTicketAvailableException ex) {
                    textFieldManagerGet.setText("Ticket with that id does not exist");
                } catch (TicketNotAssignedException ex) {
                    textFieldManagerGet.setText("Ticket not assigned to any user");
                }
            }
        });
        buttonModify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ticketController.changeTicketCustomerId(textFieldManagerTicketId.getText(),
                            textFieldManagerCustomerId.getText());
                    textFieldManagerGet.setText("");
                } catch (NoTicketAvailableException ex) {
                    textFieldManagerGet.setText("Ticket with that id does not exist");
                } catch (TicketNotAssignedException ex) {
                    textFieldManagerGet.setText("Ticket not assigned to any user");
                }
            }
        });
        buttonStoreBuy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ticketController.buyTicket(textFieldStoreDestination.getText(),
                            textFieldStoreCustomerId.getText());
                    textFieldStoreError.setText("");
                } catch (NoDestinationAvailableException ex) {
                    textFieldStoreError.setText("No plane with the destination");
                } catch (NoTicketAvailableException ex) {
                    textFieldStoreError.setText("No ticket available");
                }
            }
        });
        buttonViewFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textAreaView.setText("");
                List<AirplaneTicket> atList;
                if(comboBoxViewFilter.getSelectedIndex() == 0) {
                    atList = ticketController.getTickets();
                }
                else {
                    atList = ticketController.filterTicketsByStatus((TicketStatus) comboBoxViewFilter.getSelectedItem());
                }
                for ( AirplaneTicket at : atList ) {
                    textAreaView.append(at.getId() + ",");
                    textAreaView.append(at.getDestination() + ",");
                    textAreaView.append(at.getPrice().toString() + ",");
                    textAreaView.append(at.getStatus().toString() + ",");
                    textAreaView.append(at.getCustomerId() + ";");
                    textAreaView.append("\n");
                }
            }
        });
        groupViewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textAreaView.setText("");
                Map<String, List<AirplaneTicket>> atList = ticketController.groupTicketsByCustomerId();
                for ( String atKey : atList.keySet() ) {
                    textAreaView.append(atKey + ":\n");
                    for (AirplaneTicket at : atList.get(atKey)) {
                        textAreaView.append(at.getId() + ",");
                        textAreaView.append(at.getDestination() + ",");
                        textAreaView.append(at.getPrice().toString() + ",");
                        textAreaView.append(at.getStatus().toString() + ",");
                        textAreaView.append(at.getCustomerId() + ",");
                        textAreaView.append("\n");
                    }
                }
            }
        });
    }

}

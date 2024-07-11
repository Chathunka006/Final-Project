package lk.ijse.BlueDewMangementSystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.BlueDewMangementSystem.model.Order;
import lk.ijse.BlueDewMangementSystem.model.PlaceOrder;
import lk.ijse.BlueDewMangementSystem.model.ToEsier.Customer;
import lk.ijse.BlueDewMangementSystem.model.tm.CartTm;
import lk.ijse.BlueDewMangementSystem.repository.CustomerRepo;
import lk.ijse.BlueDewMangementSystem.repository.FishRepo;
import lk.ijse.BlueDewMangementSystem.repository.OrderRepo;
import lk.ijse.BlueDewMangementSystem.repository.PlaceOrderRepo;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


public class PlaceOrderFormController {

    public TextField txtContactNumber;
    public ComboBox combContact;
    public DatePicker txtDate;
    @FXML
    private AnchorPane rootNode;



    @FXML
    private TextField txtCustomerId;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnAddToCart;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnPlaceOrder;

    @FXML
    private TableView<CartTm> tblItem;

    @FXML
    private TableColumn<?, ?> colItemId;

    @FXML
    private TableColumn<?, ?> colItemNAME;

    @FXML
    private TableColumn<?, ?> colItemQty;

    @FXML
    private TableColumn<?, ?> colItemPrice;

    @FXML
    private TableColumn<?, ?> colItemTotal;

    @FXML
    private TableColumn<?, ?> colItemAction;

    @FXML
    private TextField txtOrderId;

    
//    private TextField txtDate;

    @FXML
    private TextField txtItemName;

    @FXML
    private TextField txtPrice;

    @FXML
    private ComboBox<String> combItemId;

    @FXML
    private ComboBox<String> combFishId;

    @FXML
    private TextField txtFishName;

    @FXML
    private TextField txtQty;

    @FXML
    private TableView<CartTm> tblFish;

    @FXML
    private TableColumn<?, ?> colFishId;

    @FXML
    private TableColumn<?, ?> colFishNAME;

    @FXML
    private TableColumn<?, ?> colFishQty;

    @FXML
    private TableColumn<?, ?> colFishPrice;

    @FXML
    private TableColumn<?, ?> colFishTotal;

    @FXML
    private TableColumn<?, ?> colFishAction;

    @FXML
    private TextArea txtNetBalance;

    @FXML
    private TextField txtCustomeName;


    ObservableList<CartTm> obList = FXCollections.observableArrayList();


    @FXML
    void btnBackOnAction(ActionEvent event) {

    }

  

    @FXML
    void combFishIdOnAction(ActionEvent event) {
        String value = combFishId.getValue();
        try {
            String name = FishRepo.getName(value);
            FishRepo.getprice(value);
            txtFishName.setText(name);
            txtPrice.setText( FishRepo.getprice(value));
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }

    @FXML
    void combItemIdOnAction(ActionEvent event) {

    }

    @FXML
    void tblCUSTOMEROnAction(MouseEvent event) {

    }

    public void tblCUSTOMEROnAction(javafx.scene.input.MouseEvent mouseEvent) {
    }



   /* private void setContactNumber() throws SQLException {
        List<Integer> contact = CustomerRepo.getContact();
        ObservableList<Integer> obList = FXCollections.observableArrayList();

        for (int n : contact){
            obList.add(n);
        }


        TextFields.bindAutoCompletion(txtContactNumber, obList);
    }

    public void   setItemName() throws SQLException {
        List<String> item = ItemRepo.getItem();
        ObservableList<String> obList = FXCollections.observableArrayList();

        for (String n : item){
            obList.add(n);
        }

        TextFields.bindAutoCompletion(txtItemName, obList);
    }*/
    private void getCurrentOrderId() {
        try {
            String currentId = OrderRepo.getCurrentId();

            String nextOrderId = generateNextOrderId(currentId);
            txtOrderId.setText(nextOrderId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNextOrderId(String currentId) {
        return null;
    }

    private void setDate() {
        LocalDate.now();
        txtDate.setValue(LocalDate.now());
    }

    private void setCellValueFactory() {

        colFishId.setCellValueFactory(new PropertyValueFactory<>("FishId"));
        colFishNAME.setCellValueFactory(new PropertyValueFactory<>("FishName"));
        colFishQty.setCellValueFactory(new PropertyValueFactory<>("Qty"));
        colFishPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        colFishTotal.setCellValueFactory(new PropertyValueFactory<>("Total"));
    }

    private void getFish(){
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> tankList = FishRepo.getId();
            for (String id : tankList) {
                obList.add(id);
            }
            combFishId.setItems(obList);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    private void getContact(){
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<Integer> tankList = CustomerRepo.getContact();
            for (Integer id : tankList) {
                obList.add(String.valueOf(id));
            }
            combContact.setItems(obList);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


   /* private void getCustomerNIC() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> nicList = CustomerRepo.getNIC();

            for (String nic : nicList) {
                obList.add(nic);
            }

            combNIC.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void getItemIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> itemIdList = ItemRepo.getItemId();

            for (String itemId : itemIdList) {
                obList.add(itemId);
            }
            combItemId.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/




    @FXML
    void btnAddToCartOnAction(ActionEvent event) {


        String FishID = (String) combFishId.getValue();
        String FishName = txtFishName.getText();
        int Qty = Integer.parseInt(txtQty.getText());
        double Price = Double.parseDouble(txtPrice.getText());
        double Total = Qty * Price;

        CartTm cartTm = new CartTm(FishID,FishName,Qty,Price,Total);

        System.out.println(cartTm);

        obList.add(cartTm);

        tblFish.setItems(obList);
//        JFXButton btnRemove = new JFXButton("remove");
//        btnRemove.
//        btnRemove.setCursor(Cursor.HAND);

//        btnRemove.setOnAction((e) -> {
//            ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
//            ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);
//
//            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();
//
//            if (type.orElse(no) == yes) {
//                int selectedIndex = tblFish.getSelectionModel().getSelectedIndex();
//                obList.remove(selectedIndex);
//
//                tblFish.refresh();
//                calculateNetTotal();
//            }
//        });

////        for (int i = 0; i < tblFish.getItems().size(); i++) {
////            if (FishID.equals(colFishId.getCellData(i))) {
////
////                CartTm tm = obList.get(i);
////                Qty += tm.getQty();
////                Total = Qty * Price;
////
////                tm.setQty(Qty);
////                tm.setTotal(Total);
////
////                tblFish.refresh();
////
////                calculateNetTotal();
////                return;
////            }
////        }
//
//
//        CartTm tm = new CartTm(FishID,FishName, Qty, Price, Total, btnRemove);
//        obList.add(tm);
//
//        tblFish.setItems(obList);
//        calculateNetTotal();
//        txtQty.setText("");



    }

    private void calculateNetTotal() {
        String netBalance = String.valueOf(0);
        for (int i = 0; i < tblFish.getItems().size(); i++) {
            netBalance += (String) colFishTotal.getCellData(i);
        }
        txtNetBalance.setText(String.valueOf(netBalance));
    }


   /* @FXML
    void btnBackOnAction (ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        Stage stage = (Stage) rootNode.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Main Form");
        stage.centerOnScreen();
    }*/

    @FXML
    void btnAddOnAction  (ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/CustomerForm.fxml"));
        Stage stage = (Stage) rootNode.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Customer Form");
        stage.centerOnScreen();
    }

    public void initialize() {
        getFish();
        getContact();
        setCellValueFactory();
        txtDate.setValue(LocalDate.now());
    }

    public void combContactOnAction(ActionEvent actionEvent) {
        String value = (String) combContact.getValue();
        try {
            System.out.println(value);
            Customer customer = CustomerRepo.getIdandName(Integer.parseInt(value));
            txtCustomerId.setText(customer.getCusId());
            txtCustomeName.setText(customer.getCusName());
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) throws ParseException, SQLException {

        String FishID = (String) combFishId.getValue();
        String FishName = txtFishName.getText();
        int Qty = Integer.parseInt(txtQty.getText());
        double Price = Double.parseDouble(txtPrice.getText());
        double Total = Qty * Price;

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();

        PlaceOrderRepo.placeOrder(new PlaceOrder(new Order(
                txtOrderId.getText(),
                date,
                txtCustomerId.getText(),
                Double.parseDouble(txtPrice.getText()),
                Integer.parseInt(txtQty.getText())
        ),obList));

    }


    /*  @FXML
      void combItemIdOnAction (ActionEvent event){
          String item_id = (String) combItemId.getValue();

          try {
              Item item = ItemRepo.searchItem(item_id);

              txtItemName.setText(item.getName());
              txtQtyOnHand.setText(String.valueOf(item.getQty()));
              txtPrice.setText(String.valueOf(item.getPrice()));

          } catch (SQLException e) {
              throw new RuntimeException(e);
          }
      }*/
    

   

   /* @FXML
    void btnPlaceOrderOnAction (ActionEvent event) throws  SQLException {
        String OrderId = txtOrderId.getText();
        Date date = Date.valueOf(LocalDate.now());
        String CustomerId = txtCustomerId.getText();
        double Price = Double.parseDouble(txtPrice.getText());
        int Qty = Integer.parseInt(txtQty.getText());



        Order order = new Order(OrderId,date,CustomerId, Price,Qty);

        List<ItemOrderInfo>  odList = new ArrayList<>();

        for (int i = 0; i < tblItem.getItems().size(); i++){
            CartTm tm = obList.get(i);

            ItemOrderInfo od = new ItemOrderInfo(
                    OrderId,

                    tm.getQty(),
                    tm.getPrice()
            );
            ItemList.add(od);
        }

        PlaceOrder po = new PlaceOrder(order, );
        try {
            boolean isPlaced = PlaceOrderRepo.placeOrder(po);
            if(isPlaced) {
                new Alert(Alert.AlertType.CONFIRMATION, "Order Placed!").show();
                obList.clear();
                tblItem.setItems(obList);
                calculateNetTotal();
                //makeOrderBill();
                getCurrentOrderId();
                //   generateBill(orderId);

            }else {
                new Alert(Alert.AlertType.WARNING, "Order Placed Unsuccessfully!").show();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    public void combContactOnAction(ActionEvent actionEvent) {
    }
*/
  /*  private void generateBill(String orderId) {
        try {
            String netTotal = calculateNetTotal(orderId);

            JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/Report/PlaceOrder.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

                Map<String, Object> parameters = new HashMap<>();
                parameters.put("orderId", orderId);
                parameters.put("total", netTotal);

                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, DbConnection.getInstance().getConnection());
                JasperViewer.viewReport(jasperPrint, false);

         } catch (JRException | SQLException e) {
            throw new RuntimeException(e);
        }
    }*/

  /*  public void makeOrderBill() throws JRException, SQLException {
        JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/reports/ORDERBILL.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        Map<String,Object> data = new HashMap<>();
        data.put("OrderID",txtOrderId.getText());

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, data, DbConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint, false);
    }

    public void btnGenareteBillOnAction(ActionEvent actionEvent) throws JRException, SQLException {

    }
   /* private String calculateNetTotal(String orderId) {
        return PlaceOrderRepo.calculateNetTotal(orderId);
    }*/
}



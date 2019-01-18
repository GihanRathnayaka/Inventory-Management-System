package com.revivatea.controler;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.revivatea.business.BOFactory;
import com.revivatea.business.custom.ManageProductBO;
import com.revivatea.business.custom.ManageProductionBO;
import com.revivatea.db.DBConnection;
import com.revivatea.dto.ProductDTO;
import com.revivatea.dto.ProductionDetailsDTO;
import com.revivatea.view.tm.ValidationClass;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.SimpleJasperReportsContext;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.repo.FileRepositoryPersistenceServiceFactory;
import net.sf.jasperreports.repo.FileRepositoryService;
import net.sf.jasperreports.repo.PersistenceServiceFactory;
import net.sf.jasperreports.repo.RepositoryService;
import net.sf.jasperreports.view.JasperViewer;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductionDetailsControler<T> {
    @FXML
    private TableView<ProductionDetailsDTO> tblDetails;
    @FXML
    private Button btnBack;
    @FXML
    private JFXTextField txtProductionID;
    @FXML
    private JFXTextField txtDescription;
    @FXML
    private JFXTextField txtProductID;
    @FXML
    private JFXTextField txtProductName;
    @FXML
    private JFXTextField txtNumberOfPackage;
    @FXML
    private JFXTextField txtPackPrice;
    @FXML
    private JFXRadioButton rbBottele;
    @FXML
    private JFXRadioButton rbPackage;
    @FXML
    private JFXRadioButton tbBages;
    @FXML
    private  JFXTextField txtBellSheetNumber;
    @FXML
    private JFXDatePicker dtpMadeDate;
    @FXML
    private JFXDatePicker dtpExpireDate;
    @FXML
    private JFXTextField txtPackUites;
    @FXML
    private JFXTextField txtTotalValue;
    @FXML
    private JFXButton btnAdd;
    @FXML
    private JFXButton btnPlace;
    @FXML
    private JFXButton btnRemove;
    @FXML
    private JFXButton btnClear;
    @FXML
    private Label lblTotal;
    @FXML
    private JFXDatePicker dtpDate;

   private static ToggleGroup group = new ToggleGroup();
   private static ManageProductBO manageProductBO = BOFactory.getInstance().getBO(BOFactory.BOType.MANAGEPRODUCT);
   private static ManageProductionBO manageProductionBO  =BOFactory.getInstance().getBO(BOFactory.BOType.MANAGEPRODUCTIONDETAILS);


    public void initialize() {
        rbPackage.setToggleGroup(group);
        rbBottele.setToggleGroup(group);
        tbBages.setToggleGroup(group);

        tblDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("proID"));
        tblDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("bellSheetNo"));
        tblDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("nofPackage"));
        tblDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("packagePrice"));
        tblDetails.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("packageUnite"));
        tblDetails.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("total"));
        tblDetails.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("madeDate"));
        tblDetails.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("expire"));
        tblDetails.getColumns().get(8).setCellValueFactory(new PropertyValueFactory<>("packType"));
        tblDetails.getColumns().get(9).setCellValueFactory(new PropertyValueFactory<>("releaseID"));
        tblDetails.getColumns().get(10).setCellValueFactory(new PropertyValueFactory<>("recordDate"));
        tblDetails.getColumns().get(11).setCellValueFactory(new PropertyValueFactory<>("description"));


        tblDetails.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ProductionDetailsDTO>() {
            @Override
            public void changed(ObservableValue<? extends ProductionDetailsDTO> observable, ProductionDetailsDTO oldValue, ProductionDetailsDTO pd) {
                if(pd==null){return;}
                txtBellSheetNumber.setText(pd.getBellSheetNo());
                txtDescription.setText(pd.getDescription());
                txtNumberOfPackage.setText(pd.getNofPackage()+"");
                txtPackPrice.setText(pd.getPackagePrice()+"");
                txtPackUites.setText(pd.getPackageUnite()+"");
                txtProductID.setText(pd.getProID());
                txtTotalValue.setText(pd.getTotal()+"");
                dtpDate.setValue(pd.getRecordDate());
                dtpExpireDate.setValue(pd.getExpire());
                dtpMadeDate.setValue(pd.getMadeDate());
                String pt =pd.getPackType();
                if(pd.equals("Bottle")){group.selectToggle(rbBottele);}
                if(pd.equals("Package")){group.selectToggle(rbPackage);}
                if(pd.equals("Bags")){group.selectToggle(tbBages);}

                try {
                    String name = manageProductBO.getProduct(pd.getProID()).getName();
                    txtProductName.setText(name);
                } catch (Exception e) {
                    Logger.getLogger("").log(Level.SEVERE, null, e);
                }

                btnAdd.setText("Update");
                txtProductID.setEditable(false);
                txtProductionID.setEditable(false);

            }
        });

    }
    @FXML
    private void txtProductID_OnAction(ActionEvent actionEvent) {

        if(!txtProductID.getText().trim().isEmpty()){
            try {
                ProductDTO product = manageProductBO.getProduct(txtProductID.getText().trim().toUpperCase());
                if(product==null){
                    new Alert(Alert.AlertType.ERROR,"Invalid Product Code Please Check Again !",ButtonType.OK).showAndWait();
                    txtProductID.requestFocus();
                    return;
                }
                txtProductName.setText(product.getName());
                txtProductionID.setEditable(false);
                txtNumberOfPackage.requestFocus();
            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE, null, e);
            }
        }else {
            new Alert(Alert.AlertType.ERROR,"Please Enter Product Code !",ButtonType.OK).showAndWait();

        }
    }

    @FXML
    private void btnAdd_OnAction(ActionEvent actionEvent) {

        if(txtProductionID.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter Release ID !",ButtonType.OK).showAndWait();
            txtProductionID.requestFocus();
            return;
        }
        if(txtProductID.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter Product Code !",ButtonType.OK).showAndWait();
            txtProductID.requestFocus();
            return;
        }
        if(txtBellSheetNumber.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter Product Code !",ButtonType.OK).showAndWait();
            txtBellSheetNumber.requestFocus();
            return;
        }
        if(txtBellSheetNumber.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter Bell Sheet Number !",ButtonType.OK).showAndWait();
            txtBellSheetNumber.requestFocus();
            return;
        }
        if(txtNumberOfPackage.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter Number Of Package !",ButtonType.OK).showAndWait();
            txtNumberOfPackage.requestFocus();
            return;
        }
        if(txtPackPrice.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter Packag Price !",ButtonType.OK).showAndWait();
            txtPackPrice.requestFocus();
            return;
        }
        if(txtPackUites.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter Packag Unit !",ButtonType.OK).showAndWait();
            txtPackUites.requestFocus();
            return;
        }
        if(txtTotalValue.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Total Value Is Empty !",ButtonType.OK).showAndWait();
            txtTotalValue.requestFocus();
            return;
        }
        if(null==dtpDate.getValue()){
            new Alert(Alert.AlertType.ERROR,"Please Enter  Date  !", ButtonType.OK).showAndWait();
            dtpDate.requestFocus();
            return;
        }
        if(null==dtpExpireDate.getValue()){
            new Alert(Alert.AlertType.ERROR,"Please Enter Epiry  Date  !", ButtonType.OK).showAndWait();
            dtpExpireDate.requestFocus();
            return;
        }
        if(null==dtpMadeDate.getValue()){
            new Alert(Alert.AlertType.ERROR,"Please Enter Made Date  !", ButtonType.OK).showAndWait();
            dtpMadeDate.requestFocus();
            return;
        }

        if(group.getSelectedToggle() == null){
            new Alert(Alert.AlertType.ERROR,"Please Select Package Type !", ButtonType.OK).showAndWait();
            return;
        }


        String productID =txtProductID.getText().trim();
        String description =txtDescription.getText().trim();
        String releaseID =txtProductionID.getText().trim().toUpperCase();
        String bellsheet =txtBellSheetNumber.getText().trim();
        LocalDate date =dtpDate.getValue();
        LocalDate expiry =dtpExpireDate.getValue();
        LocalDate madeDate =dtpMadeDate.getValue();
        String packType =((RadioButton) group.getSelectedToggle()).getText();

       if(!ValidationClass.isINT(txtNumberOfPackage.getText().trim())){
           new Alert(Alert.AlertType.ERROR,"Please Enter Number Of Package !",ButtonType.OK).showAndWait();
           txtNumberOfPackage.requestFocus();
           return;
       }
        if(!ValidationClass.isDouble(txtPackPrice.getText().trim())){
            new Alert(Alert.AlertType.ERROR,"Please Enter package Price !",ButtonType.OK).showAndWait();
            txtPackPrice.requestFocus();
            return;
        }
        if(!ValidationClass.isDouble(txtPackUites.getText().trim())){
            new Alert(Alert.AlertType.ERROR,"Please Enter package Units !",ButtonType.OK).showAndWait();
            txtPackUites.requestFocus();
            return;
        }
        double packPrice =Double.parseDouble(txtPackPrice.getText().trim());
        double nofPack =Double.parseDouble(txtNumberOfPackage.getText().trim());
        double packUnit =Double.parseDouble(txtPackUites.getText().trim());
        double total =Double.parseDouble(txtTotalValue.getText().trim());

        try {
           if(!manageProductionBO.findMaterialRealseIsIn(releaseID)){
               new Alert(Alert.AlertType.ERROR,"Check Material Release Number  !",ButtonType.OK).showAndWait();
                txtProductionID.requestFocus();
                txtProductionID.setEditable(true);
                return;
           }
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }

        ProductionDetailsDTO DTO = new ProductionDetailsDTO(1, date, bellsheet, nofPack, packPrice, packUnit, total, madeDate, expiry, packType, description, productID, releaseID);

        if(!btnAdd.getText().equals("Add")){

            int row = tblDetails.getSelectionModel().getSelectedIndex();
            ObservableList<ProductionDetailsDTO> items = tblDetails.getItems();
            List<ProductionDetailsDTO> temp = new ArrayList<>();
            int index =0;
            for (ProductionDetailsDTO m:items ) {
                if(row==index){
                    temp.add(DTO) ;
                    continue;
                }

                temp.add(m);
                index++;
            }
            tblDetails .setItems(FXCollections.observableArrayList(temp));
            tblDetails.refresh();

        }else {
            tblDetails.getItems().add(DTO);
        }


        btnAdd.setText("Add");
        txtProductID.setEditable(true);
        txtProductionID.setEditable(false);
        txtNumberOfPackage.clear();
        txtPackUites.clear();
        txtDescription.clear();
        txtTotalValue.clear();
        txtBellSheetNumber.clear();
        txtProductName.clear();
        txtPackUites.clear();
        txtPackPrice.clear();
        dtpMadeDate.setValue(null);
        dtpExpireDate.setValue(null);
        dtpDate.setValue(null);
        group.selectToggle(null);
        calculateTotal();



    }

    @FXML
    private void btnRemove_OnAction(ActionEvent actionEvent) {

        if(!tblDetails.getSelectionModel().isEmpty()){
            tblDetails.getItems().remove(tblDetails.getSelectionModel().getSelectedIndex());
            tblDetails.refresh();

        }else {
            new Alert(Alert.AlertType.ERROR," Select Delete Line From Table ").showAndWait();
        }

        btnAdd.setText("Add");
        txtProductID.setEditable(true);
        txtProductionID.setEditable(false);
        txtNumberOfPackage.clear();
        txtPackUites.clear();
        txtDescription.clear();
        txtTotalValue.clear();
        txtBellSheetNumber.clear();
        txtProductName.clear();
        txtPackUites.clear();
        txtPackPrice.clear();
        dtpMadeDate.setValue(null);
        dtpExpireDate.setValue(null);
        dtpDate.setValue(null);
        group.selectToggle(null);
        calculateTotal();

    }

    @FXML
    private void btnPlace_OnAction(ActionEvent actionEvent) {
        ObservableList<ProductionDetailsDTO> items = tblDetails.getItems();
        if(items.size()<=0){
            new Alert(Alert.AlertType.ERROR,"First You have to Add Items !",ButtonType.OK).showAndWait();
            txtProductionID.setEditable(true);
            txtProductID.setEditable(true);
            txtProductionID.requestFocus();
            return;
        }

        ArrayList<ProductionDetailsDTO>temp=new ArrayList<>();
        for (ProductionDetailsDTO dto:items) {
            temp.add(dto);
        }

        try {
            boolean result = manageProductionBO.saveProductionDetails(temp);
            if(result){
                new Alert(Alert.AlertType.CONFIRMATION,"Successfully Recorded Production Information",ButtonType.OK).showAndWait();
                SimpleJasperReportsContext ctx = new SimpleJasperReportsContext();
                File file = new File("report");
                FileRepositoryService fileRepositoryService = new FileRepositoryService(ctx,file.getAbsolutePath(), false);
                ctx.setExtensions(RepositoryService.class, Collections.singletonList(fileRepositoryService));
                ctx.setExtensions(PersistenceServiceFactory.class, Collections.singletonList(FileRepositoryPersistenceServiceFactory.getInstance()));
                File jasperReport = new File("report/MaterialRelease.jasper");
                HashMap hashMap  =new HashMap();
                hashMap.put("releaseID",txtProductionID.getText().trim());
                JasperReport compiledReport = (JasperReport) JRLoader.loadObject(ctx,jasperReport);
                JasperPrint filledReport = JasperFillManager.getInstance(ctx).fill(compiledReport,hashMap, DBConnection.getInstance());
                JasperViewer.viewReport(filledReport, false);

            }else {
                new Alert(Alert.AlertType.ERROR,"Not Recorded Production Information Error",ButtonType.OK).showAndWait();

            }
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }

        rest();

    }

    @FXML
    private void txtPackPrice_On_Key_Typed(KeyEvent keyEvent) {
        if(!txtNumberOfPackage.getText().trim().isEmpty()){

          if(! ValidationClass.isDouble(txtNumberOfPackage.getText().trim())){
              new Alert(Alert.AlertType.ERROR,"Invalid Number  !",ButtonType.OK).showAndWait();
                txtNumberOfPackage.requestFocus();
                txtTotalValue.clear();
                return;
          }
            if(! ValidationClass.isDouble(txtPackPrice.getText().trim())){
                new Alert(Alert.AlertType.ERROR,"Invalid Number  !",ButtonType.OK).showAndWait();
                txtTotalValue.clear();
                txtPackPrice.requestFocus();
                return;
            }

            double price =Double.parseDouble(txtPackPrice.getText().trim());
            double pQty =Double.parseDouble(txtNumberOfPackage.getText().trim());
            txtTotalValue.setText(""+(pQty*price));


        }else {
            new Alert(Alert.AlertType.ERROR,"First Enter Number Of Packages !",ButtonType.OK).showAndWait();

        }
    }

    @FXML
    private void btnClear_OnAction(ActionEvent actionEvent) {
        rest();
    }

    @FXML
    private void txtProductionID_On_Action(ActionEvent actionEvent) {
    }
    @FXML
    private void btnBack_OnAction(ActionEvent actionEvent) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/com/revivatea/view/HomePage.fxml"));
        } catch (IOException e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
            System.out.print("Error");
        }
        Scene subScene = new Scene(root);
        Stage primaryStage = (Stage) btnBack.getScene().getWindow();
        primaryStage.setScene(subScene);
        primaryStage.centerOnScreen();

        TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
        tt.setFromX(-subScene.getWidth());
        tt.setToX(0);
        tt.play();
    }

    private void rest(){
        txtProductionID.setEditable(true);
        btnAdd.setText("Add");
        txtProductID.setEditable(true);
        txtNumberOfPackage.clear();
        txtPackUites.clear();
        txtDescription.clear();
        txtTotalValue.clear();
        txtBellSheetNumber.clear();
        txtProductName.clear();
        txtPackUites.clear();
        txtPackPrice.clear();
        dtpMadeDate.setValue(null);
        dtpExpireDate.setValue(null);
        dtpDate.setValue(null);
        group.selectToggle(null);
       tblDetails.setItems(null);
        lblTotal.setText("Total :.................");

    }

    private void calculateTotal(){
        ObservableList<ProductionDetailsDTO> items = tblDetails.getItems();
        if(items==null){lblTotal.setText("Total :.................");  return;}
        double total =0;
        for (ProductionDetailsDTO mrd:items ) {
            total=total+mrd.getTotal();
        }
        lblTotal.setText("Total : "+total);
    }


}

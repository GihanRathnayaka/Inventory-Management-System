package com.revivatea.controler;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.revivatea.business.BOFactory;
import com.revivatea.business.Converter;
import com.revivatea.business.custom.ManageMaterialReceiveBO;
import com.revivatea.business.custom.ManageRawMaterialBO;
import com.revivatea.business.custom.ManageSupplierBO;
import com.revivatea.db.DBConnection;
import com.revivatea.dto.*;
import com.revivatea.entity.Supplier;
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

public class MaterialReceivedControler<T> {
    @FXML
    private JFXTextField txtPackSize;
    @FXML
    private JFXRadioButton rbKg;
    @FXML
    private JFXRadioButton rbLiter;
    @FXML
    private JFXRadioButton rbNum;
    @FXML
    private Label lblTotal;
    @FXML
    private JFXTextField txtMatReceiveID;
    @FXML
    private Button btnBack;
    @FXML
    private JFXTextField txtDate;

    @FXML
    private JFXTextField txtCompanyName;
    @FXML
    private JFXTextField txtMaterialName;
    @FXML
    private JFXTextField txtDescription;
    @FXML
    private TableView<MaterialReceiveDetailsDTO> tblDetails;
    @FXML
    private JFXTextField txttotal;
    @FXML
    private JFXTextField txtSupplierID;
    @FXML
    private JFXTextField txtMaterialID;
    @FXML
    private JFXTextField txtQty;
    @FXML
    private JFXTextField txtPrice;
    @FXML
    private JFXDatePicker dptMadeDate;
    @FXML
    private JFXDatePicker dtpExpiredate;
    @FXML
    private JFXButton btnAdd;
    @FXML
    private JFXButton btnRemove;
    @FXML
    private JFXButton btnPlace;
    @FXML
    private JFXButton btnClear;

    private static ManageMaterialReceiveBO manageMaterialReceiveBO = BOFactory.getInstance().getBO(BOFactory.BOType.MANAGEMATERIALRECEIVE);
    private static  ManageSupplierBO manageSupplierBO = BOFactory.getInstance().getBO(BOFactory.BOType.ManageSUPPLIER) ;
    private static  ManageRawMaterialBO manageRawMaterialBO =BOFactory.getInstance().getBO(BOFactory.BOType.MANAGERAWMATERIAL);
    static ToggleGroup group = new ToggleGroup();
    public void initialize(){
        setMatReceiveID();
        rbKg.setToggleGroup(group);
        rbLiter.setToggleGroup(group);
        rbNum.setToggleGroup(group);
        txtDate.setText(LocalDate.now()+"");
        tblDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("matID"));
        tblDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("madeDate"));
        tblDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("expireDate"));
        tblDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("price"));
        tblDetails.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblDetails.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("packSize"));
        tblDetails.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("unitType"));
        tblDetails.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("total"));

        tblDetails.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<MaterialReceiveDetailsDTO>() {
            @Override
            public void changed(ObservableValue<? extends MaterialReceiveDetailsDTO> observable, MaterialReceiveDetailsDTO ov, MaterialReceiveDetailsDTO mrd) {
                if(mrd==null){return;}
                txtMaterialID.setText(mrd.getMatID());
                txtPrice.setText(mrd.getPrice()+"");
                txtQty.setText(mrd.getQty()+"");
                txttotal.setText(mrd.getTotal()+"");
                dptMadeDate.setValue(mrd.getMadeDate());
                dtpExpiredate.setValue(mrd.getExpireDate());
                txtPackSize.setText(mrd.getPackSize()+"");

                try {
                    RawMaterialDTO rowmaterial = manageRawMaterialBO.getRowmaterial(mrd.getMatID());
                    txtMaterialName.setText(rowmaterial.getName());
                } catch (Exception e) {
                    Logger.getLogger("").log(Level.SEVERE, null, e);
                }

                btnAdd.setText("Update");

            }
        });

    }

    @FXML
    private void txtSupplierID_OnAction(ActionEvent actionEvent) {
        if(!txtSupplierID.getText().trim().isEmpty()){
            try {
                SupplierDTO supplier = manageSupplierBO.getSupplier(txtSupplierID.getText().trim().toUpperCase());
                if(supplier==null){
                    System.out.println(supplier);
                    new Alert(Alert.AlertType.ERROR,"Invalid Supplier ID Please ReCheck Agian", ButtonType.OK).showAndWait();
                    txtSupplierID.requestFocus();
                    return;
                }
                txtCompanyName.setText(supplier.getCompanyName());
                txtSupplierID.setText(supplier.getSuplierID());
                txtSupplierID.setEditable(false);

            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE, null, e);
            }
        }else {
            new Alert(Alert.AlertType.CONFIRMATION,"Please Enter Your Supplier ID !", ButtonType.OK).showAndWait();

        }

    }

    @FXML
    private void txtMaterialID_OnAction(ActionEvent actionEvent) {
        if(!txtMaterialID.getText().trim().isEmpty()){
            try {
                RawMaterialDTO rowmaterial = manageRawMaterialBO.getRowmaterial(txtMaterialID.getText().trim().toUpperCase());
                if(rowmaterial==null){
                    //System.out.println(material);
                    new Alert(Alert.AlertType.ERROR,"Invalid Material ID Please ReCheck Aging", ButtonType.OK).showAndWait();
                    txtMaterialID.requestFocus();
                    return;
                }
                txtMaterialID.setText(rowmaterial.getRowMatID());
                txtMaterialName.setText(rowmaterial.getName());
                txtMaterialID.setEditable(false);
                txtQty.requestFocus();


            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE, null, e);
            }
        }else {
            new Alert(Alert.AlertType.CONFIRMATION,"Please Enter Your RawMaterial ID !", ButtonType.OK).showAndWait();

        }

    }

    @FXML
    private void btnAdd_OnAction(ActionEvent actionEvent) {

        if(txtSupplierID.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter Supplier ID !", ButtonType.OK).showAndWait();
            txtSupplierID.requestFocus();
            txtSupplierID.setEditable(true);
            return;
        }
        if(txtMaterialID.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter Material ID !", ButtonType.OK).showAndWait();
            txtMaterialID.requestFocus();
            txtMaterialID.setEditable(true);
            return;
        }
        if(txtQty.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter Qty !", ButtonType.OK).showAndWait();
            txtQty.requestFocus();
            txtQty.setEditable(true);
            return;
        }
        if(txtPrice.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter Pirce !", ButtonType.OK).showAndWait();
            txtPrice.requestFocus();
            txtPrice.setEditable(true);
            return;
        }
        if(null==dptMadeDate.getValue()){
            new Alert(Alert.AlertType.ERROR,"Please Enter Made Date !", ButtonType.OK).showAndWait();
            dptMadeDate.requestFocus();
            return;
        }

        if(null==dtpExpiredate.getValue()){
            new Alert(Alert.AlertType.ERROR,"Please Enter Expire Date  !", ButtonType.OK).showAndWait();
            dtpExpiredate.requestFocus();
            return;
        }

        if(!ValidationClass.isDouble(txtQty.getText().trim())){
            new Alert(Alert.AlertType.ERROR,"Please Enter Qty Valid !", ButtonType.OK).showAndWait();
            txtQty.requestFocus();
            txtQty.setEditable(true);
            System.out.println("");
            return;
        }


        if (!ValidationClass.isDouble(txtPrice.getText().trim())) {
            new Alert(Alert.AlertType.ERROR, "Please Enter Valid Price !", ButtonType.OK).showAndWait();
            txtPrice.requestFocus();
            txtPrice.setEditable(true);
            System.out.println("");
            return;
        }

        if(txtPackSize.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter Pack Size !", ButtonType.OK).showAndWait();
            txtPackSize.requestFocus();
            return;
        }

        if(!ValidationClass.isINT(txtPackSize.getText().trim())){
            new Alert(Alert.AlertType.ERROR, "Please Enter Valid Pack Size Number !", ButtonType.OK).showAndWait();
            txtPackSize.requestFocus();
            return;
        }

        if(group.getSelectedToggle()==null){
            new Alert(Alert.AlertType.ERROR,"Please Select Unit Type ", ButtonType.OK).showAndWait();
            return;
        }

            String matID = txtMaterialID.getText().trim();
            LocalDate madeDate = dptMadeDate.getValue();
            LocalDate expireDate = dtpExpiredate.getValue();
            double price = Double.parseDouble(txtPrice.getText().trim());
            double qty= Double.parseDouble(txtQty.getText().trim());
            double total= Double.parseDouble(txttotal.getText().trim());
            int packSize =Integer.parseInt(txtPackSize.getText().trim());
            String pcakType =((RadioButton) group.getSelectedToggle()).getText();



        if(ValidationClass.NagativeCheck(qty)){
            new Alert(Alert.AlertType.ERROR,"Please Enter Qty Valid !", ButtonType.OK).showAndWait();
            txtQty.requestFocus();
            txtQty.setEditable(true);
            return;
        }
        if(ValidationClass.NagativeCheck(price)){
            txtPrice.requestFocus();
            txtPrice.setEditable(true);
            new Alert(Alert.AlertType.ERROR, "Please Enter Valid Price !", ButtonType.OK).showAndWait();
            return;
        }




         MaterialReceiveDetailsDTO materialReceiveDetailsDTO = new MaterialReceiveDetailsDTO(madeDate, expireDate, price, qty, qty, matID, total,packSize,pcakType);

       if( isAllreadyAdd(matID)&&btnAdd.getText().equals("Add")){
           new Alert(Alert.AlertType.ERROR, "AllReady Added Please Select And Upadate  !", ButtonType.OK).showAndWait();
           txttotal.clear();
           txtQty.clear();
           txtPrice.clear();
           txtMaterialID.clear();
           txtMaterialID.setEditable(true);
           txtMaterialName.clear();
           dptMadeDate.setValue(null);
           dtpExpiredate.setValue(null);
           txtPackSize.setText("");
           group.selectToggle(null);
           return;
       }

       if(btnAdd.getText().equals("Update")){

           int row = tblDetails.getSelectionModel().getSelectedIndex();
           ObservableList<MaterialReceiveDetailsDTO> items = tblDetails.getItems();
           List<MaterialReceiveDetailsDTO> temp = new ArrayList<>();
           int index =0;
           for (MaterialReceiveDetailsDTO m:items ) {
               if(row==index){
                  temp.add(materialReceiveDetailsDTO) ;
                  continue;
               }

               temp.add(m);
               index++;
           }
           tblDetails.setItems(FXCollections.observableArrayList(temp));

       }else {
           tblDetails.getItems().add(materialReceiveDetailsDTO);
       }



       calculateTotal();
       txttotal.clear();
       txtQty.clear();
       txtPrice.clear();
       txtMaterialID.clear();
       txtMaterialID.setEditable(true);
       txtMaterialName.clear();
       dptMadeDate.setValue(null);
       dtpExpiredate.setValue(null);
       btnAdd.setText("Add");
       txtPackSize.setText("");

    }

    @FXML
    private void btnRemove_OnAction(ActionEvent actionEvent) {
        if(!tblDetails.getSelectionModel().isEmpty()){
            tblDetails.getItems().remove(tblDetails.getSelectionModel().getSelectedIndex());
            tblDetails.refresh();
            btnAdd.setText("Add");
            calculateTotal();
            txttotal.clear();
            txtQty.clear();
            txtPrice.clear();
            txtMaterialID.clear();
            txtMaterialID.setEditable(true);
            txtMaterialName.clear();
            dptMadeDate.setValue(null);
            dtpExpiredate.setValue(null);
            btnAdd.setText("Add");
            group.selectToggle(null);



        }
    }

    @FXML
    private void btnPlace_OnAction(ActionEvent actionEvent) {

        if(txtMatReceiveID.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter Valid Supplier ID !", ButtonType.OK).showAndWait();
            txtMatReceiveID.requestFocus();
            return;
        }
        if(txtSupplierID.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter Qty Valid !", ButtonType.OK).showAndWait();
            txtMatReceiveID.requestFocus();
            return;
        }

        if(txtCompanyName.getText().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter Valid Supplier ID !", ButtonType.OK).showAndWait();
            txtMatReceiveID.requestFocus();
            return;
        }

        if(tblDetails.getItems().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Add Raw Materila Details !", ButtonType.OK).showAndWait();
            return;
        }

        String matReceiveID =txtMatReceiveID.getText().trim();
        LocalDate date =LocalDate.parse(txtDate.getText());
        String suplierID =txtSupplierID.getText().trim();
        String[] split = lblTotal.getText().split("[:]");
        String description =txtDescription.getText().trim();
        double total =Double.parseDouble(split[1]);

        MaterialReceiveDTO matReceive= new MaterialReceiveDTO(matReceiveID, date, description, total, suplierID);
        ArrayList<MaterialReceiveDetailsDTO>list=getAllMatDetails();

        try {
            boolean result = manageMaterialReceiveBO.saveMaterialReceive(matReceive, list);
            if(result){
                new Alert(Alert.AlertType.CONFIRMATION,"Material Receive Details Succesfully Recorded !").showAndWait();

                SimpleJasperReportsContext ctx = new SimpleJasperReportsContext();
                File file = new File("report");
                FileRepositoryService fileRepositoryService = new FileRepositoryService(ctx,file.getAbsolutePath(), false);

                ctx.setExtensions(RepositoryService.class, Collections.singletonList(fileRepositoryService));
                ctx.setExtensions(PersistenceServiceFactory.class, Collections.singletonList(FileRepositoryPersistenceServiceFactory.getInstance()));

                File jasperReport = new File("report/MaterialReceiveDetails.jasper");
                HashMap hashMap  =new HashMap();
                hashMap.put("Sid",txtMatReceiveID.getText().trim());

                JasperReport compiledReport = (JasperReport) JRLoader.loadObject(ctx,jasperReport);

                JasperPrint filledReport = JasperFillManager.getInstance(ctx).fill(compiledReport,hashMap, DBConnection.getInstance());

                JasperViewer.viewReport(filledReport, false);

            }else {
                new Alert(Alert.AlertType.ERROR,"Material Receive Details Not Recorded !").showAndWait();

            }

        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }

        reset();


    }
    @FXML
    private void btnClear_OnAction(ActionEvent actionEvent) {
        reset();

    }


    @FXML
    private void btnBack_OnAction(ActionEvent actionEvent) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/com/revivatea/view/HomePage.fxml"));
        } catch (IOException e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }
        Scene subScene = new Scene(root);
        Stage primaryStage = (Stage) btnBack.getScene().getWindow();
        primaryStage.setScene(subScene);
        primaryStage.centerOnScreen();
        System.out.println("");
        TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
        tt.setFromX(-subScene.getWidth());
        tt.setToX(0);
        tt.play();
    }


    private void setMatReceiveID(){
        try {
            txtMatReceiveID.setText(manageMaterialReceiveBO.getMatrialReceiveID());
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }
    }

    public void txtQty_OnKeyPress(KeyEvent keyEvent) {
    }

    public void txtPrice_OnAction(KeyEvent keyEvent) {
        if(!ValidationClass.isDouble(txtQty.getText().trim())){
            new Alert(Alert.AlertType.ERROR,"Please Enter Qty Valid !", ButtonType.OK).showAndWait();
            txtQty.requestFocus();
            txtQty.setEditable(true);
            return;
        }


        if(!txtPrice.getText().trim().isEmpty()){

            if(!ValidationClass.isDouble(txtPrice.getText().trim())){
            new Alert(Alert.AlertType.ERROR,"Please Enter Valid Price !", ButtonType.OK).showAndWait();
            txtPrice.requestFocus();
            txtPrice.setEditable(true);
            return;
         }
            double price =Double.parseDouble(txtPrice.getText());
            double qty =Double.parseDouble(txtQty.getText());
            txttotal.setText(price*qty+"");
        }else {
            txttotal.setText("");
        }


    }


    private  boolean isAllreadyAdd(String id){

        ObservableList<MaterialReceiveDetailsDTO> items = tblDetails.getItems();
        if(items==null){return false;}

        for (MaterialReceiveDetailsDTO dto:items ) {
            if(dto.getMatID().equals(id)){
                return true;
            }
        }

        return false;
    }

    private void calculateTotal() {
        ObservableList<MaterialReceiveDetailsDTO> items = tblDetails.getItems();
        if (items == null) {
            return;
        }
        double total=0;

        for (MaterialReceiveDetailsDTO dto : items) {
            total=total+dto.getTotal();
        }
        lblTotal .setText("Total : "+total);
    }

    private ArrayList<MaterialReceiveDetailsDTO> getAllMatDetails(){
        ObservableList<MaterialReceiveDetailsDTO> items = tblDetails.getItems();
        ArrayList<MaterialReceiveDetailsDTO> temp = new ArrayList<>();
        for (MaterialReceiveDetailsDTO dto: items) {
            temp.add(dto);
        }
        return temp;
    }

    private void reset(){
        txtSupplierID.setEditable(true);
        txtMaterialID.setEditable(true);
      //  group.selectToggle(null);
        tblDetails.refresh();
        btnAdd.setText("Add");
        calculateTotal();
        txttotal.clear();
        txtQty.clear();
        txtPrice.clear();
        txtMaterialID.clear();
        txtMaterialName.clear();
        dptMadeDate.setValue(null);
        dtpExpiredate.setValue(null);
        btnAdd.setText("Add");
        lblTotal.setText("Total :............................");
        tblDetails.setItems(null);
        txtCompanyName.clear();
        txtSupplierID.clear();
        txtPackSize.clear();
        group.selectToggle(null);
        setMatReceiveID();
        txtDescription.clear();


    }

}

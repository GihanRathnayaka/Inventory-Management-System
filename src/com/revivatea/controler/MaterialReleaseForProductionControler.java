package com.revivatea.controler;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.revivatea.business.BOFactory;
import com.revivatea.business.custom.ManageMaterialReleaseBO;
import com.revivatea.business.custom.ManageRawMaterialBO;
import com.revivatea.db.DBConnection;
import com.revivatea.dto.MaterialReceiveDetailsDTO;
import com.revivatea.dto.MaterialReleaseDTO;
import com.revivatea.dto.MaterialReleaseDetailsDTO;
import com.revivatea.dto.RawMaterialDTO;
import com.revivatea.entity.MaterialRelease;
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

public class MaterialReleaseForProductionControler <T>{
    @FXML
    private Label lblTotal;
    @FXML
    private JFXTextField txtReleaseDetailsID;
    @FXML
    private JFXTextField txtPackagePrice;

    @FXML
    private JFXTextField txtTotalValue;
    @FXML
    private Button btnBack;
    @FXML
    private JFXTextField txtMatID;
    @FXML
    private JFXTextField txtMatName;
    @FXML
    private JFXDatePicker dtpDate;
    @FXML
    private JFXTextField txtMatReleaseID;
    @FXML
    private JFXTextField txtAviablePackage;
    @FXML
    private JFXTextField txtRequireQty;
    @FXML
    private TableView<MaterialReleaseDetailsDTO> tblMatrelease;
    @FXML
    private JFXTextField txtDescription;
    @FXML
    private JFXButton btnAdd;
    @FXML
    private JFXButton btnRemove;
    @FXML
    private JFXButton btnPlace;
    @FXML
    private JFXButton btnClear;
    @FXML
    private JFXTextField txtTotalUinits;

    private static ManageMaterialReleaseBO manageMaterialReleaseBO = BOFactory.getInstance().getBO(BOFactory.BOType.MATERIALRELEASE);
    private static ManageRawMaterialBO manageRawMaterialBO =BOFactory.getInstance().getBO(BOFactory.BOType.MANAGERAWMATERIAL);

    public void initialize(){
        setDetailsID();
        setMatNumber();
        tblMatrelease.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("releaseID"));
        tblMatrelease.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("matRdID"));
        tblMatrelease.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("rowMatID"));
        tblMatrelease.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("releaseqty"));
        tblMatrelease.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("total"));

        tblMatrelease.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<MaterialReleaseDetailsDTO>() {
            @Override
            public void changed(ObservableValue<? extends MaterialReleaseDetailsDTO> observable, MaterialReleaseDetailsDTO oldValue, MaterialReleaseDetailsDTO mrd) {
                if(mrd==null){return;}
                txtMatID.setText(mrd.getRowMatID());
                txtRequireQty.setText(mrd.getReleaseqty()+"");
                txtTotalValue.setText(""+mrd.getTotal());
                txtReleaseDetailsID.setText(mrd.getMatRdID());
                txtMatID.setEditable(false);
                btnAdd.setText("Update");
                try {
                    RawMaterialDTO rmat = manageRawMaterialBO.getRowmaterial(mrd.getRowMatID());
                    txtMatName.setText(rmat.getName());
                    txtAviablePackage.setText(rmat.getAviableQTY()+"");
                } catch (Exception e) {
                    Logger.getLogger("").log(Level.SEVERE, null, e);
                }

            }

        });


    }



    @FXML
    private void txtMatID_OnAction(ActionEvent actionEvent) {
        if(!txtMatID.getText().trim().isEmpty()){

            try {
                RawMaterialDTO rowmaterial = manageRawMaterialBO.getRowmaterial(txtMatID.getText().trim().toUpperCase());
                if(rowmaterial==null){
                    new Alert(Alert.AlertType.ERROR,"Your Enter RawMaterial Number is Incorrect Please Check !", ButtonType.OK).showAndWait();
                    txtMatID.requestFocus();
                    return;
                }
                txtMatID.setText(rowmaterial.getRowMatID());
                txtMatName.setText(rowmaterial.getName());
                txtAviablePackage.setText(rowmaterial.getAviableQTY()+"");
                txtRequireQty.requestFocus();
                txtMatID.setEditable(false);
            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE, null, e);
            }

        }else {
            new Alert(Alert.AlertType.ERROR,"Your Enter RawMaterial Number !", ButtonType.OK).showAndWait();
            return;
        }

    }


    @FXML
    private void btnAdd_OnAction(ActionEvent actionEvent) {

        if(txtTotalValue.getText().trim().isEmpty()||txtRequireQty.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter the Release Quantity",ButtonType.OK).showAndWait();
            txtRequireQty.requestFocus();
            return;
        }

//        if(!isAllreadyAdd()){
//            new Alert(Alert.AlertType.ERROR," Details Added ! ",ButtonType.OK).showAndWait();
//            return;
//        }




            String matRdID = txtReleaseDetailsID.getText().trim();
            double releaseqty = Double.parseDouble(txtRequireQty.getText().trim());
            double total = Double.parseDouble(txtTotalValue.getText().trim());
            String releaseID = txtMatReleaseID.getText().trim();
            String rowMatID = txtMatID.getText().trim();

        MaterialReleaseDetailsDTO detailsDTO = new MaterialReleaseDetailsDTO(matRdID, releaseqty, total, releaseID, rowMatID);


        txtMatID.setEditable(true);

        if(btnAdd.getText().equals("Add")) {
            tblMatrelease.getItems().add(detailsDTO);
            new Alert(Alert.AlertType.CONFIRMATION, "Details Added ! ", ButtonType.OK).showAndWait();

        }else {

            int row = tblMatrelease.getSelectionModel().getSelectedIndex();
            ObservableList<MaterialReleaseDetailsDTO> items = tblMatrelease.getItems();
            List<MaterialReleaseDetailsDTO> temp = new ArrayList<>();
            int index =0;
            for (MaterialReleaseDetailsDTO m:items ) {
                if(row==index){
                    temp.add(detailsDTO) ;
                    continue;
                }

                temp.add(m);
                index++;
            }
            tblMatrelease .setItems(FXCollections.observableArrayList(temp));
            tblMatrelease.refresh();
        }

        btnAdd.setText("Add");
        txtMatID.clear();
        txtTotalValue.clear();
        txtRequireQty.clear();
        txtMatName.clear();
        txtAviablePackage.clear();
        setMaterialDetailsNumber();
        calculateTotal();
    }

    @FXML
    private void btnRemove_OnAction(ActionEvent actionEvent) {
        if(!tblMatrelease.getSelectionModel().isEmpty()){
            tblMatrelease.getItems().remove(tblMatrelease.getSelectionModel().getSelectedIndex());
            tblMatrelease.refresh();

        }
        setDetailsID();
        btnAdd.setText("Add");
        txtMatID.clear();
        txtTotalValue.clear();
        txtRequireQty.clear();
        txtMatName.clear();
        txtAviablePackage.clear();
    }

    @FXML
    private void btnPlace_OnAction(ActionEvent actionEvent) {

        if(null==dtpDate.getValue()){
            new Alert(Alert.AlertType.ERROR,"Please Select Date !",ButtonType.OK).showAndWait();
            dtpDate.requestFocus();
            return;
        }

        if(txtMatReleaseID.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Invalid ID !",ButtonType.OK).showAndWait();
            txtMatReleaseID.requestFocus();
            return;
        }

        String realeseID =txtMatReleaseID.getText().trim();
        String description =txtDescription.getText().trim();
        LocalDate date =dtpDate.getValue();
        String[] split = lblTotal.getText().trim().split("[:]");
        double total =Double.parseDouble(split[1].trim());

        ObservableList<MaterialReleaseDetailsDTO> items = tblMatrelease.getItems();
        ArrayList<MaterialReleaseDetailsDTO> list = new ArrayList<>();
        for (MaterialReleaseDetailsDTO dto:items) {
            list.add(dto);
        }



        if(items.size()<0){
            new Alert(Alert.AlertType.ERROR,"Add RawMaterial Details !",ButtonType.OK).showAndWait();
            return;
        }

        try {
            boolean result = manageMaterialReleaseBO.saveMaterialRelease(new MaterialReleaseDTO(realeseID, date, total, description), list);

            if(result){
                new Alert(Alert.AlertType.CONFIRMATION,"Details Saved Successfully !",ButtonType.OK).showAndWait();

                SimpleJasperReportsContext ctx = new SimpleJasperReportsContext();
                File file = new File("report");
                FileRepositoryService fileRepositoryService = new FileRepositoryService(ctx,file.getAbsolutePath(), false);

                ctx.setExtensions(RepositoryService.class, Collections.singletonList(fileRepositoryService));
                ctx.setExtensions(PersistenceServiceFactory.class, Collections.singletonList(FileRepositoryPersistenceServiceFactory.getInstance()));

                File jasperReport = new File("report/MaterialRelease.jasper");
                HashMap hashMap  =new HashMap();
                hashMap.put("releaseID",txtMatReleaseID.getText().trim());

                JasperReport compiledReport = (JasperReport) JRLoader.loadObject(ctx,jasperReport);

                JasperPrint filledReport = JasperFillManager.getInstance(ctx).fill(compiledReport,hashMap, DBConnection.getInstance());

                JasperViewer.viewReport(filledReport, false);

            }



        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }
        reset();
    }

    @FXML
    private void btnClear_OnAction(ActionEvent actionEvent) {

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
        System.out.println("/");
        TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
        tt.setFromX(-subScene.getWidth());
        tt.setToX(0);
        tt.play();

    }

    @FXML
    private void txtRequireQty_OnKeyReleased(KeyEvent keyEvent) {
        if(!txtMatID.getText().trim().isEmpty()){
            String matID =txtMatID.getText().trim();
            if(!ValidationClass.isINT(txtRequireQty.getText())){
                new Alert(Alert.AlertType.ERROR," Enter Number !", ButtonType.OK).showAndWait();
                txtRequireQty.requestFocus();
                return;
            }
            int qty =Integer.parseInt(txtRequireQty.getText());
            int aviableQty = (int) Double.parseDouble(txtAviablePackage.getText().trim());

            if(aviableQty<qty){
                new Alert(Alert.AlertType.ERROR," Enter Quantity Not Available !", ButtonType.OK).showAndWait();
                txtTotalValue.clear();
                txtRequireQty.requestFocus();
                return;
            }

            try {
                double total = manageMaterialReleaseBO.getQuantiyValue(matID, qty);
                txtTotalValue.setText(""+total);

            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE, null, e);
            }

        }else {
            new Alert(Alert.AlertType.ERROR," Enter Number  !", ButtonType.OK).showAndWait();
        }

    }


    private void setMatNumber(){
        try {
            txtMatReleaseID.setText(manageMaterialReleaseBO.materialReleaseNumber());
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }
    }

    private void setDetailsID(){

        try {
            txtReleaseDetailsID.setText(manageMaterialReleaseBO.materialReleaseDetailNumber());
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }
    }

    private boolean isAllreadyAdd(){
        ObservableList<MaterialReleaseDetailsDTO> items = tblMatrelease.getItems();
        if(items==null){return true;}
        for (MaterialReleaseDetailsDTO mrd:items ) {
            if(mrd.getRowMatID().equals(txtMatID.getText().trim())){
                return false;
            }
        }
       return true;
    }

    private void setMaterialDetailsNumber(){

        ObservableList<MaterialReleaseDetailsDTO> items = tblMatrelease.getItems();
        String txtPart=null;
        for (MaterialReleaseDetailsDTO dto:items) {
            txtPart=dto.getMatRdID();
            System.out.println(dto);
        }
        txtReleaseDetailsID.setText( txtPart.substring(0,5)+(1+Integer.parseInt(txtPart.substring(5))));

    }

    private void calculateTotal(){
        ObservableList<MaterialReleaseDetailsDTO> items = tblMatrelease.getItems();
        if(items==null){lblTotal.setText("Total :.................");  return;}
        double total =0;
        for (MaterialReleaseDetailsDTO mrd:items ) {
            total=total+mrd.getTotal();
        }
        lblTotal.setText("Total : "+total);
    }

    private void reset(){
        setDetailsID();
        btnAdd.setText("Add");
        txtMatID.clear();
        txtTotalValue.clear();
        txtRequireQty.clear();
        txtMatName.clear();
        txtAviablePackage.clear();
        txtMatReleaseID.clear();
        txtMatID.clear();
        txtMatID.setEditable(true);
        txtMatReleaseID.setEditable(true);
        tblMatrelease.setItems(null);
        txtReleaseDetailsID.clear();
        setDetailsID();
        txtDescription.clear();

    }

}

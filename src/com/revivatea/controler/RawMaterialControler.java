package com.revivatea.controler;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.revivatea.business.BOFactory;
import com.revivatea.business.custom.ManageRawMaterialBO;
import com.revivatea.business.custom.impl.ManageMaterialReceiveBOImpl;
import com.revivatea.db.DBConnection;
import com.revivatea.dto.MaterialReceiveDTO;
import com.revivatea.dto.MaterialReceiveDetailsDTO;
import com.revivatea.dto.RawMaterialDTO;
import com.revivatea.view.tm.RawMaterialTM;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;
import net.sf.jasperreports.engine.*;
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

public class RawMaterialControler {
    public JFXButton btnView;
    @FXML
    private JFXTextField txtDescription;
    @FXML
    private JFXTextField txtAvibaleQty;
    @FXML
    private JFXTextField description;
    @FXML
    private JFXTextField txtMaterilalNumber;
    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXDatePicker dtpRegDate;
    @FXML
    private Button btnBack;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private JFXButton btnDalete;
    @FXML
    private JFXButton btnCrear;
    @FXML
    private TableView<RawMaterialTM> tblMaterials;

    private static ManageRawMaterialBO rawMaterialBO = BOFactory.getInstance().getBO(BOFactory.BOType.MANAGERAWMATERIAL);
    private static ManageMaterialReceiveBOImpl manageMaterialReceiveBO =BOFactory.getInstance().getBO(BOFactory.BOType.MANAGEMATERIALRECEIVE);


    public void initialize() {
        setMatNumber();
        loadAll();
        btnDalete.setDisable(true);
        btnUpdate.setDisable(true);
        btnSave.setDisable(false);
        tblMaterials.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("rowMatID"));
        tblMaterials.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblMaterials.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("regDate"));
        tblMaterials.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("aviableQTY"));
        tblMaterials.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("descriptiion"));

        tblMaterials.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<RawMaterialTM>() {
            @Override
            public void changed(ObservableValue<? extends RawMaterialTM> observable, RawMaterialTM oldValue, RawMaterialTM rmat) {
                if(rmat==null){return;}
                txtMaterilalNumber.setText(rmat.getRowMatID());
                txtName.setText(rmat.getName());
                txtAvibaleQty.setText(rmat.getAviableQTY()+"");
                txtDescription.setText(rmat.getDescriptiion());
                dtpRegDate.setValue(rmat.getRegDate());
                btnDalete.setDisable(false);
                btnUpdate.setDisable(false);
                btnSave.setDisable(true);

            }
        });


    }
    @FXML
    private void btnSave_Onaction(ActionEvent actionEvent) {
        if(txtMaterilalNumber.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter Material Number !", ButtonType.OK).showAndWait();
            txtMaterilalNumber.requestFocus();
            return;
        }
        if(txtName.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter Material Name !", ButtonType.OK).showAndWait();
            txtName.requestFocus();
            return;
        }
        if(null==dtpRegDate.getValue()){
            new Alert(Alert.AlertType.ERROR,"Please Enter Material Registered Date !", ButtonType.OK).showAndWait();
            dtpRegDate.requestFocus();
            return;
        }

        String matNumber =txtMaterilalNumber.getText().trim();
        String name=txtName.getText().trim();
        double aviableQTY =Double.parseDouble(txtAvibaleQty.getText());
        String description =txtDescription.getText().trim();
        LocalDate regdate =dtpRegDate.getValue();

        try {
            boolean result = rawMaterialBO.createRowmaterial(new RawMaterialDTO(matNumber, name, regdate, aviableQTY, description));
            if(result){
                new Alert(Alert.AlertType.CONFIRMATION,"Successfully Created New Rawmaerial Type !", ButtonType.OK).showAndWait();

            }else{
                new Alert(Alert.AlertType.ERROR,"Not Created New Rawmaerial Type Error !", ButtonType.OK).showAndWait();

            }
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }

       rest();
    }
    @FXML
    private void btnUpdate_OnAction(ActionEvent actionEvent) {
        if(txtMaterilalNumber.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter Material Number !", ButtonType.OK).showAndWait();
            txtMaterilalNumber.requestFocus();
            return;
        }
        if(txtName.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter Material Name !", ButtonType.OK).showAndWait();
            txtName.requestFocus();
            return;
        }
        if(null==dtpRegDate.getValue()){
            new Alert(Alert.AlertType.ERROR,"Please Enter Material Registered Date !", ButtonType.OK).showAndWait();
            dtpRegDate.requestFocus();
            return;
        }

        String matNumber =txtMaterilalNumber.getText().trim();
        String name=txtName.getText().trim();
        double aviableQTY =Double.parseDouble(txtAvibaleQty.getText());
        String description =txtDescription.getText().trim();
        LocalDate regdate =dtpRegDate.getValue();

        try {
            boolean result = rawMaterialBO.updateRowmaterial(new RawMaterialDTO(matNumber, name, regdate, aviableQTY, description));
            if(result){
                new Alert(Alert.AlertType.CONFIRMATION,"Successfully Update Rawmaerial Type !", ButtonType.OK).showAndWait();

            }else{
                new Alert(Alert.AlertType.ERROR,"Not  Updated  Rawmaerial Type Error !", ButtonType.OK).showAndWait();

            }
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }

        rest();
    }

    @FXML
    private void btnDalete_OnAction(ActionEvent actionEvent) {

        if(!btnSave.isDisable()){
            new Alert(Alert.AlertType.ERROR,"Please Select One Form the Table to Delete !", ButtonType.OK).showAndWait();
            return;
        }




        try {
            List<MaterialReceiveDetailsDTO> all = manageMaterialReceiveBO.getAllMaterialRecievieDetails();

            if(all!=null) {

                for (MaterialReceiveDetailsDTO dto : all) {
                    if (dto.getMatID().equals(txtMaterilalNumber.getText().trim())) {
                        new Alert(Alert.AlertType.ERROR, "Can't Delete In a Relation !", ButtonType.OK).showAndWait();
                        return;
                    }
                }
            }
            boolean result = rawMaterialBO.deleteRowmaterial(txtMaterilalNumber.getText().trim());
            if(result){
                new Alert(Alert.AlertType.CONFIRMATION,"Successfully Deleted !", ButtonType.OK).showAndWait();

            }else{
                new Alert(Alert.AlertType.ERROR,"Not  Deleted  Rawmaerial Type Error !", ButtonType.OK).showAndWait();

            }
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }

        rest();
    }

    @FXML
    private void btnCrear_OnAction(ActionEvent actionEvent) {
        rest();
    }

    @FXML
    private void btnBack_OnAction(ActionEvent actionEvent) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/com/revivatea/view/HomePage.fxml"));
        } catch (IOException e) {
            System.out.println("Error !");
            Logger.getLogger("").log(Level.SEVERE, null, e);
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

    private void loadAll(){
        try {
            List<RawMaterialDTO> allRowmaterial = rawMaterialBO.getAllRowmaterial();
            if(allRowmaterial==null){tblMaterials.setItems(null);return;}
            ArrayList<RawMaterialTM>temp = new ArrayList<>();
            for (RawMaterialDTO r:allRowmaterial ) {
              temp.add(new RawMaterialTM(r.getRowMatID(),r.getName(),r.getRegDate(),r.getAviableQTY(),r.getDescriptiion())) ;
            }
            ObservableList<RawMaterialTM> list= FXCollections.observableArrayList(temp);
            tblMaterials.setItems(list);

        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }
    }

    private void rest(){
        txtAvibaleQty.setText("0");
        txtName.clear();
        txtMaterilalNumber.clear();
        txtDescription.clear();
        dtpRegDate.setValue(null);
        btnDalete.setDisable(true);
        btnSave.setDisable(false);
        btnUpdate.setDisable(true);
        loadAll();
        setMatNumber();
    }

    private void setMatNumber(){
        try {
            txtMaterilalNumber.setText(rawMaterialBO.getMatNumber());
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }
    }

    public void btnView_OnAction(ActionEvent actionEvent) throws Exception {

        SimpleJasperReportsContext ctx = new SimpleJasperReportsContext();
        File file = new File("report");
        FileRepositoryService fileRepositoryService = new FileRepositoryService(ctx,file.getAbsolutePath(), false);

        ctx.setExtensions(RepositoryService.class, Collections.singletonList(fileRepositoryService));
        ctx.setExtensions(PersistenceServiceFactory.class, Collections.singletonList(FileRepositoryPersistenceServiceFactory.getInstance()));

        File jasperReport = new File("report/MaterialList.jasper");
        HashMap hashMap  =new HashMap();
        hashMap.put("MatID",txtMaterilalNumber.getText().trim());

        JasperReport compiledReport = (JasperReport) JRLoader.loadObject(ctx,jasperReport);

        JasperPrint filledReport = JasperFillManager.getInstance(ctx).fill(compiledReport,hashMap, DBConnection.getInstance());

        JasperViewer.viewReport(filledReport, false);
    }
}

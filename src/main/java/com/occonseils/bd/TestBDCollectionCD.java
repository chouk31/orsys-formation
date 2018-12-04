/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.occonseils.bd;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;
import org.dbunit.Assertion;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.AmbiguousTableNameException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


/**
 *
 * @author Administrateur
 */
public class TestBDCollectionCD {

    private static IDatabaseConnection dbUnitConnection = null;
    private static InputStream inputStreamXML = null;
    private static final FlatXmlDataSetBuilder xmlDSBuilder = new FlatXmlDataSetBuilder();
    private static IDataSet initialDataSet = null;
    private static IDataSet finalExpectedDataSet = null;
    private static IDataSet databaseDataSet = null;

    private static WebDriver driver;
    private static String baseUrl;

    public static void connection() throws SQLException, DatabaseUnitException
    {        
        Connection jdbcConnection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cdcol", "root", "");
        // l'objet jdbcConnection est une instance de java.sql.Connection 
        dbUnitConnection = new DatabaseConnection(jdbcConnection);
    }

    public static void loadDataSets() throws FileNotFoundException, DataSetException {
        xmlDSBuilder.setCaseSensitiveTableNames(false);
        inputStreamXML = new FileInputStream("initial_dataset.xml");
        initialDataSet = xmlDSBuilder.build(inputStreamXML);

        inputStreamXML = new FileInputStream("final_dataset.xml");
        finalExpectedDataSet = xmlDSBuilder.build(inputStreamXML);
    }

    public static void prepareTestDataBase() throws DataSetException, DatabaseUnitException, SQLException {
        DatabaseOperation.CLEAN_INSERT.execute(dbUnitConnection, initialDataSet);
    }

    public static void saveDataSet() throws AmbiguousTableNameException, FileNotFoundException, IOException, DataSetException {
        // dbUnitConnection est une instance de IDataBaseConnection 
        QueryDataSet partialDataSet = new QueryDataSet(dbUnitConnection);
        partialDataSet.addTable("cds");
        //partialDataSet.addTable("nomTable2"); 
        OutputStream out = new FileOutputStream("final_dataset.xml");
        FlatXmlDataSet.write(partialDataSet, out);
    }

    @BeforeClass
    public static void setUp() throws Exception {
        connection();
        //saveDataSet();
        loadDataSets();
        prepareTestDataBase();

        // On indique où est le driver pour chrome
        System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
       
        // Préparation du FirefoxDriver
        driver = new ChromeDriver();
        baseUrl = "http://localhost/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        
        
    }

    @Ignore
    @Test
    public void verifyTableContent() throws SQLException, DataSetException, MalformedURLException, DatabaseUnitException {

        //Ajout par selenium d'un CD
        driver.get(baseUrl + "/xampp/cds.php");
        driver.findElement(By.name("interpret")).clear();
        driver.findElement(By.name("interpret")).sendKeys("Charles TRENET");
        driver.findElement(By.name("titel")).clear();
        driver.findElement(By.name("titel")).sendKeys("Nationale 7");
        driver.findElement(By.name("jahr")).clear();
        driver.findElement(By.name("jahr")).sendKeys("1952");
        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();

        
        // Recupération du dataset  de la table CDS de la base de test en cours
        databaseDataSet = dbUnitConnection.createDataSet();
        ITable actualTable = databaseDataSet.getTable("cds");

        // Chargement du dataset attendu
        ITable expectedTable = finalExpectedDataSet.getTable("cds");

        // Comparaison des 2 datasets
        Assertion.assertEqualsIgnoreCols(expectedTable, actualTable, new String[]{"id"});

    }

    @AfterClass
    public static void closeDatabase() throws SQLException {
        dbUnitConnection.close();
        // Quitter le firefoxDriver
        driver.quit();
        //
        //serveur.stop();
    }

}

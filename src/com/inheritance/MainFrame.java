/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inheritance;

import com.inheritance.database.DBResourceManager;
import com.inheritance.database.DatabaseDAO;
import com.inheritance.heirs.Deceased;
import com.inheritance.heirs.Deceased.Asset;
import com.inheritance.heirs.Heir;
import com.inheritance.heirs.Person;
import com.inheritance.rules.BlockingRules;
import com.inheritance.rules.InheritanceException;
import com.inheritance.rules.StandardRules;
import com.inheritance.util.TableCellListener;
import com.inheritance.util.WindowSetter;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.AbstractCellEditor;
import javax.swing.Action;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Jevison7x
 */
public class MainFrame extends JFrame
{
    private DBResourceManager manager = new DBResourceManager();
    private DatabaseDAO dao;
    private boolean notSaved;
    protected String htmlText;
    protected String standardRulesHtmlText;
    private final HeirsTableModel heirsTableModel;
    private DefaultComboBoxModel genderCBModel;
    private DefaultComboBoxModel<String> maleHeirsComboBoxModel;
    private DefaultComboBoxModel<String> femaleHeirsComboBoxModel;
    private JComboBox genderComboBox;
    private JComboBox<String> maleHeirsComboBox;
    private JComboBox<String> femaleHeirsComboBox;
    private int noOfHeirsRows;
    private int noOfAssetsRows;
    private AssetsTableModel assetsTableModel;
    private int xPos, yPos;

    /**
     * Creates new form MainFrame
     */
    public MainFrame()
    {
        this.dao = new DatabaseDAO();
        this.setDefaultLookAndFeel();
        this.heirsTableModel = new HeirsTableModel();
        this.assetsTableModel = new AssetsTableModel();
        this.initComponents();
        this.setHeirsTableColumnWidths();
        this.setAssetsTableColumnWidths();
        this.loadGenderComboBoxModel();
        this.loadGenderComboBox();
        this.loadMaleHeirsComboBoxModel();
        this.loadFemaleHeirsComboBoxModel();
        this.loadHeirComboBox();
        this.addHeirsTableCellListener();
        this.addAssetsTableCellListener();
        this.setNumericColumnsAllignment();
        this.resetRulesTextArea("StandardRules.html");
        this.rulesTextPane.setContentType("text/html");
        this.rulesTextPane.setText(htmlText);
        this.resetHeirsTextArea("StandardHeirs.html");
        this.standardHeirsTextPane.setContentType("text/html");
        this.standardHeirsTextPane.setText(standardRulesHtmlText);
        this.centralizeWindow();
    }

    public void setNotSaved(boolean b)
    {
        this.notSaved = true;
    }

    private void centralizeWindow()
    {
        WindowSetter.centraliedWindow(this);
    }

    private void setDefaultLookAndFeel()
    {
        WindowSetter.setWindowsLookAndFeel(this);
    }

    private void resetRulesTextArea(String fileName)
    {
        DataInputStream in = null;
        try
        {
            //Create a string builder
            StringBuilder sb = new StringBuilder();
            InputStream is = MainFrame.class.getResourceAsStream(fileName);
            // Get the object of DataInputStream
            in = new DataInputStream(is);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            //Read File Line By Line
            while((strLine = br.readLine()) != null)
                //Append the line to the string builder
                sb.append(strLine);
            //Set the htmlText field to the StringBuilder's content
            this.htmlText = sb.toString();
        }
        catch(IOException e)
        {
            //Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
        finally
        {
            //Close the input stream
            if(in != null)
                try
                {
                    in.close();
                }
                catch(IOException ioxcp)
                {
                    System.out.println("IOException has occured!");
                    ioxcp.printStackTrace(System.err);
                }
        }
    }

    private void resetHeirsTextArea(String fileName)
    {
        DataInputStream in = null;
        try
        {
            //Create a string builder
            StringBuilder sb = new StringBuilder();
            InputStream is = MainFrame.class.getResourceAsStream(fileName);
            // Get the object of DataInputStream
            in = new DataInputStream(is);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            //Read File Line By Line
            while((strLine = br.readLine()) != null)
                //Append the line to the string builder
                sb.append(strLine);
            //Set the htmlText field to the StringBuilder's content
            this.standardRulesHtmlText = sb.toString();
        }
        catch(IOException e)
        {
            //Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
        finally
        {
            //Close the input stream
            if(in != null)
                try
                {
                    in.close();
                }
                catch(IOException ioxcp)
                {
                    System.out.println("IOException has occured!");
                    ioxcp.printStackTrace(System.err);
                }
        }
    }

    private void setHeirsTableColumnWidths()
    {
        int snIndex = this.heirsTableModel.findColumn(HeirsTableModel.SN);
        int nameIndex = this.heirsTableModel.findColumn(HeirsTableModel.HEIR_NAME);
        int genderIndex = this.heirsTableModel.findColumn(HeirsTableModel.GENDER);
        int typeIndex = this.heirsTableModel.findColumn(HeirsTableModel.HEIR_TYPE);

        this.heirsTable.getColumnModel().getColumn(snIndex).setPreferredWidth(2);
        this.heirsTable.getColumnModel().getColumn(nameIndex).setPreferredWidth(250);
        this.heirsTable.getColumnModel().getColumn(genderIndex).setPreferredWidth(80);
        this.heirsTable.getColumnModel().getColumn(typeIndex).setPreferredWidth(150);
    }

    private void setAssetsTableColumnWidths()
    {
        int snIndex = this.assetsTableModel.findColumn(AssetsTableModel.SN);
        int descriptionIndex = this.assetsTableModel.findColumn(AssetsTableModel.ASSET_DESC);
        this.assetsTable.getColumnModel().getColumn(snIndex).setPreferredWidth(2);
        this.assetsTable.getColumnModel().getColumn(descriptionIndex).setPreferredWidth(250);
    }

    private void loadGenderComboBoxModel()
    {
        this.genderCBModel = new DefaultComboBoxModel();
        this.genderCBModel.addElement("--Select Gender--");
        this.genderCBModel.addElement(Person.MALE);
        this.genderCBModel.addElement(Person.FEMALE);
    }

    private void loadGenderComboBox()
    {
        this.genderComboBox = new JComboBox(this.genderCBModel);
        int genderIndex = this.heirsTableModel.findColumn(HeirsTableModel.GENDER);
        TableColumn genderColumn = this.heirsTable.getColumnModel().getColumn(genderIndex);
        genderColumn.setCellEditor(new DefaultCellEditor(this.genderComboBox));
    }

    private void loadMaleHeirsComboBoxModel()
    {
        this.maleHeirsComboBoxModel = new DefaultComboBoxModel();
        String[] maleHeirs = Heir.getMaleHeirs();
        for(String maleHeir : maleHeirs)
            this.maleHeirsComboBoxModel.addElement(maleHeir);
    }

    private void loadFemaleHeirsComboBoxModel()
    {
        this.femaleHeirsComboBoxModel = new DefaultComboBoxModel();
        String[] femaleHeirs = Heir.getFemaleHeirs();
        for(String femaleHeir : femaleHeirs)
            this.femaleHeirsComboBoxModel.addElement(femaleHeir);
    }

    private void loadHeirComboBox()
    {
        int heirTypeIndex = this.heirsTableModel.findColumn(HeirsTableModel.HEIR_TYPE);
        TableColumn heirTypeColumn = this.heirsTable.getColumnModel().getColumn(heirTypeIndex);
        this.maleHeirsComboBox = new JComboBox<>(this.maleHeirsComboBoxModel);
        this.femaleHeirsComboBox = new JComboBox<>(this.femaleHeirsComboBoxModel);
        heirTypeColumn.setCellEditor(new CustomTableCellEditor(this.maleHeirsComboBox, this.femaleHeirsComboBox));
    }

    private class CustomTableCellEditor extends AbstractCellEditor implements TableCellEditor
    {
        private JComboBox<String> maleHeirsComboBox;
        private JComboBox<String> femaleHeirsComboBox;
        private TableCellEditor editor;

        public CustomTableCellEditor(JComboBox<String> maleHeirsComboBox, JComboBox<String> femaleHeirsComboBox)
        {
            this.maleHeirsComboBox = maleHeirsComboBox;
            this.femaleHeirsComboBox = femaleHeirsComboBox;
        }

        @Override
        public Object getCellEditorValue()
        {
            if(editor != null)
                return editor.getCellEditorValue();
            else
                return null;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)
        {
            HeirsTableModel tableModel = (HeirsTableModel)table.getModel();
            int columnIndex = tableModel.findColumn(HeirsTableModel.GENDER);
            String gender = tableModel.getValueAt(row, columnIndex);
            switch(gender)
            {
                case Person.MALE:
                    this.editor = new DefaultCellEditor(this.maleHeirsComboBox);
                    break;
                case Person.FEMALE:
                    this.editor = new DefaultCellEditor(this.femaleHeirsComboBox);
                    break;
                default:
                {

                }
            }
            return editor.getTableCellEditorComponent(table, value, isSelected, row, column);
        }
    }

    private void setNumericColumnsAllignment()
    {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        TableColumnModel columnModel = this.heirsTable.getColumnModel();
        HeirsTableModel tableModel = (HeirsTableModel)this.heirsTable.getModel();
        int snIndex = tableModel.findColumn(HeirsTableModel.SN);
        columnModel.getColumn(snIndex).setCellRenderer(rightRenderer);
    }

    private void addHeirsTableCellListener()
    {
        Action action = new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                TableCellListener tcl = (TableCellListener)e.getSource();
                int row = tcl.getRow();
                int column = tcl.getColumn();
                HeirsTableModel heirsTableModel = (HeirsTableModel)MainFrame.this.heirsTable.getModel();
                final String HEIR_NAME = HeirsTableModel.HEIR_NAME;
                final String GENDER = HeirsTableModel.GENDER;
                final String HEIR_TYPE = HeirsTableModel.HEIR_TYPE;
                String columnName = heirsTableModel.getColumnName(column);
                String data = heirsTableModel.getValueAt(row, column).trim();
                String oldData = (String)tcl.getOldValue();
                switch(columnName)
                {
                    case HEIR_NAME:
                    {
                        if(!data.isEmpty())
                        {
                            char c = data.charAt(0);
                            boolean isDigit = (c >= '0' && c <= '9');
                            if(isDigit)
                            {
                                Toolkit.getDefaultToolkit().beep();
                                JOptionPane.showMessageDialog(MainFrame.this, "Please enter a valid name!\nNames cannot start with a number.", "Error!", JOptionPane.ERROR_MESSAGE);
                                heirsTableModel.setValueAt(oldData, row, column);
                            }
                            else
                            {
                                int genderColIndex = heirsTableModel.findColumn(GENDER);
                                heirsTableModel.setCellEditable(row, genderColIndex, true);
                            }
                        }
                        else
                        {
                            Toolkit.getDefaultToolkit().beep();
                            JOptionPane.showMessageDialog(MainFrame.this, "Please enter the name!", "Error!", JOptionPane.ERROR_MESSAGE);
                            heirsTableModel.setValueAt(oldData, row, column);
                        }
                    }
                    break;
                    case GENDER:
                    {
                        switch(data)
                        {
                            case Person.MALE:
                            case Person.FEMALE:
                            {
                                int heirTypeColIndex = heirsTableModel.findColumn(HEIR_TYPE);
                                heirsTableModel.setCellEditable(row, heirTypeColIndex, true);
                                heirsTableModel.setValueAt("", row, heirTypeColIndex);
                            }
                            break;
                            default:
                            {
                                Toolkit.getDefaultToolkit().beep();
                                JOptionPane.showMessageDialog(MainFrame.this, "Please select either male or female!", "Error!", JOptionPane.ERROR_MESSAGE);
                                //tableModel.setValueAt(oldData, row, column);
                                int heirTypeColIndex = heirsTableModel.findColumn(HEIR_TYPE);
                                heirsTableModel.setCellEditable(row, heirTypeColIndex, false);
                            }
                        }
                    }
                    break;
                    case HEIR_TYPE:
                    {
                        //int genderColIndex = heirsTableModel.findColumn(GENDER);
                        //tableModel.setCellEditable(row, genderColIndex, false);
                        int heirTypeIndex = heirsTableModel.findColumn(HeirsTableModel.HEIR_TYPE);
                        TableColumn heirTypeColumn = MainFrame.this.heirsTable.getColumnModel().getColumn(heirTypeIndex);

                        String heirType = heirTypeColumn.getCellEditor().getCellEditorValue().toString();
                        heirsTableModel.setValueAt(heirType, row, column);
                        heirsTableModel.fireTableCellUpdated(row, column);
                        //JOptionPane.showMessageDialog(null, "This cell at row: " + row + " and column: " + column + " has been updated!");
                    }
                    break;
                }
            }
        };
        TableCellListener tcl = new TableCellListener(this.heirsTable, action);
    }

    private void addAssetsTableCellListener()
    {
        Action action = new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                TableCellListener tcl = (TableCellListener)e.getSource();
                int row = tcl.getRow();
                int column = tcl.getColumn();
                AssetsTableModel assetTableModel = (AssetsTableModel)MainFrame.this.assetsTable.getModel();
                final String ASSET_DESC = AssetsTableModel.ASSET_DESC;
                final String ASSET_VALUE = AssetsTableModel.ASSET_VALUE;
                String assetColumnName = assetTableModel.getColumnName(column);
                Object data = assetTableModel.getValueAt(row, column);
                Object oldData = tcl.getOldValue();

                switch(assetColumnName)
                {
                    case ASSET_DESC:
                    {
                        if(((String)data).trim().isEmpty())
                        {
                            Toolkit.getDefaultToolkit().beep();
                            JOptionPane.showMessageDialog(MainFrame.this, "Please enter the description for the asset!", "Error!", JOptionPane.ERROR_MESSAGE);
                            assetTableModel.setValueAt(oldData, row, column);
                        }
                        else
                        {
                            //Make the value column to be editable
                            int assetValueColumnIndex = assetTableModel.findColumn(ASSET_VALUE);
                            assetTableModel.setCellEditable(row, assetValueColumnIndex, true);
                        }
                    }
                    break;
                    case ASSET_VALUE:
                    {
                        try
                        {

                            if(String.valueOf(data).trim().isEmpty())
                                throw new IllegalArgumentException();
                            double assetValue = (Double)data;
                        }
                        catch(ClassCastException | IllegalArgumentException xcp)
                        {
                            Toolkit.getDefaultToolkit().beep();
                            JOptionPane.showMessageDialog(MainFrame.this, "Please enter a valid value for the property!", "Error!", JOptionPane.ERROR_MESSAGE);
                            assetTableModel.setValueAt(oldData, row, column);
                        }
                    }
                    break;
                }
            }
        };
        TableCellListener tcl = new TableCellListener(this.assetsTable, action);
    }

    private void validateDeceasedData() throws IllegalArgumentException
    {
        try
        {
            String deceasedName = this.deceasedNameTextField.getText().trim();
            if(deceasedName.length() < 2)
                throw new IllegalArgumentException("Please enter the name of the deceased.");
            int age = Integer.parseInt(this.deceasedAgeTextField.getText().trim());
            if(age < 0 || age > 180)
                throw new IllegalArgumentException("Please enter a valid age for the deceased.");
            if(!this.maleRadioBtn.isSelected() && !this.femaleRadioBtn.isSelected())
                throw new IllegalArgumentException("Please select the genter of the deceased.");
        }
        catch(NumberFormatException xcp)
        {
            throw new IllegalArgumentException("Please type in a valid number for the age of the deceased.");
        }
    }

    private void validateAssetTable()
    {
        for(int i = 0; i < this.noOfAssetsRows; i++)
        {
            AssetsTableModel tableModel = (AssetsTableModel)this.assetsTable.getModel();
            final int DESC_COL_INDEX = tableModel.findColumn(AssetsTableModel.ASSET_DESC);
            final int VAL_COL_INDEX = tableModel.findColumn(AssetsTableModel.ASSET_VALUE);
            String description = (String)tableModel.getValueAt(i, DESC_COL_INDEX);
            Double value = (Double)tableModel.getValueAt(i, VAL_COL_INDEX);
            if(description.trim().isEmpty())
                throw new IllegalArgumentException("Please enter the description for the asset at row " + (i + 1));
            else if(value == 0.0D)
                throw new IllegalArgumentException("Please enter the value of the asset at row " + (i + 1));
        }
    }

    private void validateHeirsTable()
    {
        for(int i = 0; i < this.noOfHeirsRows; i++)
        {
            HeirsTableModel tableModel = (HeirsTableModel)this.heirsTable.getModel();
            final int HEIR_NAME_INDEX = tableModel.findColumn(HeirsTableModel.HEIR_NAME);
            final int GENDER_INDEX = tableModel.findColumn(HeirsTableModel.GENDER);
            final int HEIR_TYPE_INDEX = tableModel.findColumn(HeirsTableModel.HEIR_TYPE);

            String heirName = (String)tableModel.getValueAt(i, HEIR_NAME_INDEX);
            String gender = (String)tableModel.getValueAt(i, GENDER_INDEX);
            String heirType = (String)tableModel.getValueAt(i, HEIR_TYPE_INDEX);

            if(heirName.trim().isEmpty())
                throw new IllegalArgumentException("Please enter the heir name for the heir at row " + (i + 1));
            else if(gender.isEmpty())
                throw new IllegalArgumentException("Please enter the gender for the heir at row " + (i + 1));
            else if(heirType.isEmpty())
                throw new IllegalArgumentException("Please enter the heir type for the heir at row " + (i + 1));
        }
    }

    private List<Asset> getAssets()
    {
        List<Asset> assets = new ArrayList<>();
        return assets;
    }

    private List<Heir> getHeirs()
    {
        List<Heir> heirs = new ArrayList<>();
        return heirs;
    }

    private Deceased getDeceased() throws InheritanceException
    {
        String deceasedName = this.deceasedNameTextField.getText().trim();
        String deceasedGender = this.maleRadioBtn.isSelected() ? Person.MALE : this.femaleRadioBtn.isSelected() ? Person.FEMALE : "";
        int deceasedAge = Integer.parseInt(this.deceasedAgeTextField.getText().trim());
        Deceased decesaed = new Deceased(deceasedName, deceasedAge, deceasedGender);
        decesaed.setAssets(this.getAssets());
        for(Heir heir : this.getHeirs())
            decesaed.addHeir(heir);
        return decesaed;
    }

    /**
     * ********Added by Jed 11/07/16************
     */
    private void addHeirsToDeceased(Deceased deceased) throws InheritanceException
    {
        HeirsTableModel model = (HeirsTableModel)this.heirsTable.getModel();
        int age = 5;
        int noOfRows = model.getRowCount();
        int snColumnIndex = model.findColumn(HeirsTableModel.SN);
        int heirNameColumnIndex = model.findColumn(HeirsTableModel.HEIR_NAME);
        int genderColumnIndex = model.findColumn(HeirsTableModel.GENDER);
        int heirTypeColumnIndex = model.findColumn(HeirsTableModel.HEIR_TYPE);
        for(int i = 0; i < noOfRows; i++)
        {
            String heirName = model.getValueAt(i, heirNameColumnIndex);
            String heirGender = model.getValueAt(i, genderColumnIndex);
            String heirType = model.getValueAt(i, heirTypeColumnIndex);
            if(!heirName.isEmpty() && !heirGender.isEmpty() && !heirType.isEmpty())
            {
                Heir theHeir = new Heir(heirName, age, heirGender);
                try
                {
                    theHeir.setHeirType(deceased, heirType);
                    deceased.addHeir(theHeir);
                }
                catch(InheritanceException xcp)
                {
                    throw xcp;
                }
            }
        }
    }

    private void addAssetsToDeceased(Deceased deceased)
    {
        AssetsTableModel model = (AssetsTableModel)this.assetsTable.getModel();
        int noOfRows = model.getRowCount();
        int snColumnIndex = model.findColumn(AssetsTableModel.SN);
        int assetDescColumnIndex = model.findColumn(AssetsTableModel.ASSET_DESC);
        int assetValueColumnIndex = model.findColumn(AssetsTableModel.ASSET_VALUE);
        for(int i = 0; i < noOfRows; i++)
        {
            int sNo = Integer.parseInt(model.getValueAt(i, snColumnIndex).toString());
            String assetDesc = model.getValueAt(i, assetDescColumnIndex).toString();
            String assetValue = model.getValueAt(i, assetValueColumnIndex).toString();
            Double theAssetValue = Double.parseDouble(assetValue);
            if(!assetDesc.isEmpty() && !assetValue.isEmpty())
            {
                Deceased.Asset theAsset = deceased.new Asset();
                theAsset.setAssetName(assetDesc);
                theAsset.setAssetValue(theAssetValue);
                deceased.addAsset(theAsset);
            }
        }
    }

    /**
     * ********End Added by Jed 11/07/16************
     */
    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        genderButtonGroup = new javax.swing.ButtonGroup();
        rootPanel = new javax.swing.JPanel();
        tabbedPane = new javax.swing.JTabbedPane();
        homePanel = new javax.swing.JPanel();
        titlePanel = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        subTitleLabel = new javax.swing.JLabel();
        newCaseButton = new javax.swing.JButton();
        openCaseButton = new javax.swing.JButton();
        rulesPanel = new javax.swing.JPanel();
        rulesScrollPane = new javax.swing.JScrollPane();
        rulesTextPane = new javax.swing.JTextPane();
        heirsChartPanel = new javax.swing.JPanel();
        heirsChartScrollPane = new javax.swing.JScrollPane();
        heirsChartSCPanel = new javax.swing.JPanel();
        heirsChartLabel = new javax.swing.JLabel();
        standardHeirsPanel = new javax.swing.JPanel();
        standardHeirsScrollPane = new javax.swing.JScrollPane();
        standardHeirsTextPane = new javax.swing.JTextPane();
        calculatorPanel = new javax.swing.JPanel();
        calculatorComponentsPanel = new javax.swing.JPanel();
        instructionPanel = new javax.swing.JPanel();
        instrctionsLabel = new javax.swing.JLabel();
        instructionsScrollPane = new javax.swing.JScrollPane();
        instructionsTextPane = new javax.swing.JTextPane();
        deceasedPanel = new javax.swing.JPanel();
        deceasedNameLabel = new javax.swing.JLabel();
        deceasedNameTextField = new javax.swing.JTextField();
        deceasedGenderLabel = new javax.swing.JLabel();
        maleRadioBtn = new javax.swing.JRadioButton();
        femaleRadioBtn = new javax.swing.JRadioButton();
        deceasedAgeLabel = new javax.swing.JLabel();
        deceasedAgeTextField = new javax.swing.JTextField();
        heirsTablePanel = new javax.swing.JPanel();
        assetsTableLabel = new javax.swing.JLabel();
        assetsScrollPane = new javax.swing.JScrollPane();
        assetsTable = new javax.swing.JTable();
        heirsScrollPane = new javax.swing.JScrollPane();
        heirsTable = new javax.swing.JTable();
        heirsTableLabel = new javax.swing.JLabel();
        calculatorToolBar = new javax.swing.JToolBar();
        addAssetButton = new javax.swing.JButton();
        deleteAssetButton = new javax.swing.JButton();
        newHeirButton = new javax.swing.JButton();
        deleteHeirButton = new javax.swing.JButton();
        calculateButton = new javax.swing.JButton();
        shareResultsPanel = new javax.swing.JPanel();
        resultsPanel = new javax.swing.JPanel();
        shareResultsTitleLbl = new javax.swing.JLabel();
        rsScrollPane = new javax.swing.JScrollPane();
        resultsTable = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        footerPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Islamic Inheritance Calculator");
        setIconImage(new ImageIcon(getClass().getResource("icons/allah-icon.png")).getImage());
        setUndecorated(true);

        rootPanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter()
        {
            public void mouseDragged(java.awt.event.MouseEvent evt)
            {
                mainPanelMouseDragged(evt);
            }
        });
        rootPanel.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mousePressed(java.awt.event.MouseEvent evt)
            {
                mainPanelPressed(evt);
            }
        });

        homePanel.setBackground(new java.awt.Color(51, 51, 51));

        titlePanel.setBackground(new java.awt.Color(0, 0, 0));

        titleLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/inheritance/icons/logo.png"))); // NOI18N
        titleLabel.setText("jLabel1");

        subTitleLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/inheritance/icons/logo3.png"))); // NOI18N

        javax.swing.GroupLayout titlePanelLayout = new javax.swing.GroupLayout(titlePanel);
        titlePanel.setLayout(titlePanelLayout);
        titlePanelLayout.setHorizontalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titlePanelLayout.createSequentialGroup()
                .addGap(141, 141, 141)
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 715, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(210, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titlePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(subTitleLabel)
                .addGap(210, 210, 210))
        );
        titlePanelLayout.setVerticalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titlePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(subTitleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        newCaseButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        newCaseButton.setForeground(new java.awt.Color(51, 22, 8));
        newCaseButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/inheritance/icons/add_case.png"))); // NOI18N
        newCaseButton.setText("New Case");

        openCaseButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        openCaseButton.setForeground(new java.awt.Color(0, 102, 204));
        openCaseButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/inheritance/icons/open-folder-with-document.png"))); // NOI18N
        openCaseButton.setText("Open Case");

        javax.swing.GroupLayout homePanelLayout = new javax.swing.GroupLayout(homePanel);
        homePanel.setLayout(homePanelLayout);
        homePanelLayout.setHorizontalGroup(
            homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homePanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(openCaseButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(newCaseButton, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
                .addContainerGap(857, Short.MAX_VALUE))
            .addComponent(titlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        homePanelLayout.setVerticalGroup(
            homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homePanelLayout.createSequentialGroup()
                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(newCaseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(openCaseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(92, Short.MAX_VALUE))
        );

        homePanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {newCaseButton, openCaseButton});

        tabbedPane.addTab("<html>\n    <font size=\"5\" color=\"#554400\"><b>Home</b></font>\n</html>", new javax.swing.ImageIcon(getClass().getResource("/com/inheritance/icons/home-icon.png")), homePanel); // NOI18N

        rulesTextPane.setEditable(false);
        rulesScrollPane.setViewportView(rulesTextPane);

        javax.swing.GroupLayout rulesPanelLayout = new javax.swing.GroupLayout(rulesPanel);
        rulesPanel.setLayout(rulesPanelLayout);
        rulesPanelLayout.setHorizontalGroup(
            rulesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rulesScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1066, Short.MAX_VALUE)
        );
        rulesPanelLayout.setVerticalGroup(
            rulesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rulesScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
        );

        tabbedPane.addTab("<html>\n    <font size=\"5\" color=\"#FF9900\"><b>Rules</b></font>\n</html>", new javax.swing.ImageIcon(getClass().getResource("/com/inheritance/icons/traffic-lights-icon.png")), rulesPanel); // NOI18N

        heirsChartLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        heirsChartLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/inheritance/icons/Standardheirs.jpg"))); // NOI18N

        javax.swing.GroupLayout heirsChartSCPanelLayout = new javax.swing.GroupLayout(heirsChartSCPanel);
        heirsChartSCPanel.setLayout(heirsChartSCPanelLayout);
        heirsChartSCPanelLayout.setHorizontalGroup(
            heirsChartSCPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(heirsChartLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 1047, Short.MAX_VALUE)
        );
        heirsChartSCPanelLayout.setVerticalGroup(
            heirsChartSCPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(heirsChartSCPanelLayout.createSequentialGroup()
                .addComponent(heirsChartLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 665, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 41, Short.MAX_VALUE))
        );

        heirsChartScrollPane.setViewportView(heirsChartSCPanel);

        javax.swing.GroupLayout heirsChartPanelLayout = new javax.swing.GroupLayout(heirsChartPanel);
        heirsChartPanel.setLayout(heirsChartPanelLayout);
        heirsChartPanelLayout.setHorizontalGroup(
            heirsChartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(heirsChartScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1066, Short.MAX_VALUE)
        );
        heirsChartPanelLayout.setVerticalGroup(
            heirsChartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(heirsChartScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
        );

        tabbedPane.addTab("<html>\n    <font size=\"5\" color=\"#FF0000\"><b>Heirs Chart</b></font>\n</html>", new javax.swing.ImageIcon(getClass().getResource("/com/inheritance/icons/diagram-icon.png")), heirsChartPanel); // NOI18N

        standardHeirsTextPane.setEditable(false);
        standardHeirsScrollPane.setViewportView(standardHeirsTextPane);

        javax.swing.GroupLayout standardHeirsPanelLayout = new javax.swing.GroupLayout(standardHeirsPanel);
        standardHeirsPanel.setLayout(standardHeirsPanelLayout);
        standardHeirsPanelLayout.setHorizontalGroup(
            standardHeirsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(standardHeirsPanelLayout.createSequentialGroup()
                .addComponent(standardHeirsScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1056, Short.MAX_VALUE)
                .addContainerGap())
        );
        standardHeirsPanelLayout.setVerticalGroup(
            standardHeirsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(standardHeirsPanelLayout.createSequentialGroup()
                .addComponent(standardHeirsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        tabbedPane.addTab("<html>\n    <font size=\"5\" color=\"#0099FF\"><b>Standard Heirs</b></font>\n</html>", new javax.swing.ImageIcon(getClass().getResource("/com/inheritance/icons/user-icon.png")), standardHeirsPanel); // NOI18N

        instrctionsLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        instrctionsLabel.setForeground(new java.awt.Color(0, 0, 204));
        instrctionsLabel.setText("Instructions");

        instructionsScrollPane.setViewportView(instructionsTextPane);

        deceasedNameLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        deceasedNameLabel.setForeground(new java.awt.Color(0, 0, 153));
        deceasedNameLabel.setText("Deceased's Name:");

        deceasedGenderLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        deceasedGenderLabel.setForeground(new java.awt.Color(0, 0, 153));
        deceasedGenderLabel.setText("Deceased's Gender:");

        genderButtonGroup.add(maleRadioBtn);
        maleRadioBtn.setText("Male");

        genderButtonGroup.add(femaleRadioBtn);
        femaleRadioBtn.setText("Female");

        deceasedAgeLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        deceasedAgeLabel.setForeground(new java.awt.Color(51, 0, 153));
        deceasedAgeLabel.setText("Deceased's Age:");

        javax.swing.GroupLayout deceasedPanelLayout = new javax.swing.GroupLayout(deceasedPanel);
        deceasedPanel.setLayout(deceasedPanelLayout);
        deceasedPanelLayout.setHorizontalGroup(
            deceasedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(deceasedPanelLayout.createSequentialGroup()
                .addGroup(deceasedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(deceasedPanelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(deceasedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(deceasedNameLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(deceasedGenderLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(deceasedAgeLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(deceasedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(deceasedNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(deceasedAgeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(deceasedPanelLayout.createSequentialGroup()
                        .addGap(141, 141, 141)
                        .addComponent(maleRadioBtn)
                        .addGap(18, 18, 18)
                        .addComponent(femaleRadioBtn)))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        deceasedPanelLayout.setVerticalGroup(
            deceasedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(deceasedPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(deceasedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deceasedNameLabel)
                    .addComponent(deceasedNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(deceasedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maleRadioBtn)
                    .addComponent(femaleRadioBtn)
                    .addComponent(deceasedGenderLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(deceasedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deceasedAgeLabel)
                    .addComponent(deceasedAgeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout heirsTablePanelLayout = new javax.swing.GroupLayout(heirsTablePanel);
        heirsTablePanel.setLayout(heirsTablePanelLayout);
        heirsTablePanelLayout.setHorizontalGroup(
            heirsTablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 578, Short.MAX_VALUE)
        );
        heirsTablePanelLayout.setVerticalGroup(
            heirsTablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 124, Short.MAX_VALUE)
        );

        assetsTableLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        assetsTableLabel.setForeground(new java.awt.Color(0, 0, 153));
        assetsTableLabel.setText("Assets Table");

        assetsTable.setModel(this.assetsTableModel);
        assetsScrollPane.setViewportView(assetsTable);

        heirsTable.setModel(this.heirsTableModel);
        heirsScrollPane.setViewportView(heirsTable);
        if (heirsTable.getColumnModel().getColumnCount() > 0)
        {
            heirsTable.getColumnModel().getColumn(1).setResizable(false);
        }

        heirsTableLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        heirsTableLabel.setForeground(new java.awt.Color(0, 0, 204));
        heirsTableLabel.setText("Heirs");

        javax.swing.GroupLayout instructionPanelLayout = new javax.swing.GroupLayout(instructionPanel);
        instructionPanel.setLayout(instructionPanelLayout);
        instructionPanelLayout.setHorizontalGroup(
            instructionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(instructionPanelLayout.createSequentialGroup()
                .addGroup(instructionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(instructionPanelLayout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addComponent(instrctionsLabel))
                    .addGroup(instructionPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(instructionsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(instructionPanelLayout.createSequentialGroup()
                        .addGap(148, 148, 148)
                        .addComponent(assetsTableLabel))
                    .addGroup(instructionPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(assetsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(instructionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(instructionPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(instructionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(deceasedPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(heirsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 568, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(instructionPanelLayout.createSequentialGroup()
                        .addGap(255, 255, 255)
                        .addComponent(heirsTableLabel)))
                .addGap(163, 163, 163)
                .addComponent(heirsTablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        instructionPanelLayout.setVerticalGroup(
            instructionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, instructionPanelLayout.createSequentialGroup()
                .addComponent(deceasedPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(instructionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(instructionPanelLayout.createSequentialGroup()
                        .addComponent(heirsTablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, instructionPanelLayout.createSequentialGroup()
                        .addGap(0, 4, Short.MAX_VALUE)
                        .addComponent(heirsTableLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(heirsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, instructionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(instrctionsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(instructionsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(assetsTableLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(assetsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );

        calculatorToolBar.setBackground(new java.awt.Color(204, 204, 204));
        calculatorToolBar.setFloatable(false);
        calculatorToolBar.setRollover(true);

        addAssetButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        addAssetButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/inheritance/icons/add_case.png"))); // NOI18N
        addAssetButton.setText("Add Asset");
        addAssetButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        addAssetButton.setFocusable(false);
        addAssetButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                addAssetButtonActionPerformed(evt);
            }
        });
        calculatorToolBar.add(addAssetButton);

        deleteAssetButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        deleteAssetButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/inheritance/icons/Cancel_button.png"))); // NOI18N
        deleteAssetButton.setText("Remove Asset");
        deleteAssetButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        deleteAssetButton.setFocusable(false);
        deleteAssetButton.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        deleteAssetButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                deleteAssetButtonActionPerformed(evt);
            }
        });
        calculatorToolBar.add(deleteAssetButton);

        newHeirButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        newHeirButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/inheritance/icons/customers_icon.png"))); // NOI18N
        newHeirButton.setText("Add Heir");
        newHeirButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        newHeirButton.setFocusable(false);
        newHeirButton.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        newHeirButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                newHeirButtonActionPerformed(evt);
            }
        });
        calculatorToolBar.add(newHeirButton);

        deleteHeirButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        deleteHeirButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/inheritance/icons/Cancel_button.png"))); // NOI18N
        deleteHeirButton.setText("Remove Heir");
        deleteHeirButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        deleteHeirButton.setFocusable(false);
        deleteHeirButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                deleteHeirButtonActionPerformed(evt);
            }
        });
        calculatorToolBar.add(deleteHeirButton);

        calculateButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        calculateButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/inheritance/icons/calculator-icon_1.png"))); // NOI18N
        calculateButton.setText("Calculate");
        calculateButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        calculateButton.setFocusable(false);
        calculateButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                calculateButtonActionPerformed(evt);
            }
        });
        calculatorToolBar.add(calculateButton);

        javax.swing.GroupLayout calculatorComponentsPanelLayout = new javax.swing.GroupLayout(calculatorComponentsPanel);
        calculatorComponentsPanel.setLayout(calculatorComponentsPanelLayout);
        calculatorComponentsPanelLayout.setHorizontalGroup(
            calculatorComponentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(instructionPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1022, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(calculatorToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        calculatorComponentsPanelLayout.setVerticalGroup(
            calculatorComponentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, calculatorComponentsPanelLayout.createSequentialGroup()
                .addComponent(calculatorToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(instructionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout calculatorPanelLayout = new javax.swing.GroupLayout(calculatorPanel);
        calculatorPanel.setLayout(calculatorPanelLayout);
        calculatorPanelLayout.setHorizontalGroup(
            calculatorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(calculatorPanelLayout.createSequentialGroup()
                .addComponent(calculatorComponentsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 44, Short.MAX_VALUE))
        );
        calculatorPanelLayout.setVerticalGroup(
            calculatorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(calculatorComponentsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        tabbedPane.addTab("<html>\n    <font size=\"5\" color=\"#009900\"><b>Calculator</b></font>\n</html>", new javax.swing.ImageIcon(getClass().getResource("/com/inheritance/icons/calculator-icon.png")), calculatorPanel); // NOI18N

        shareResultsTitleLbl.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        shareResultsTitleLbl.setText("Share Results");

        resultsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String []
            {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        rsScrollPane.setViewportView(resultsTable);

        javax.swing.GroupLayout resultsPanelLayout = new javax.swing.GroupLayout(resultsPanel);
        resultsPanel.setLayout(resultsPanelLayout);
        resultsPanelLayout.setHorizontalGroup(
            resultsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resultsPanelLayout.createSequentialGroup()
                .addContainerGap(192, Short.MAX_VALUE)
                .addGroup(resultsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, resultsPanelLayout.createSequentialGroup()
                        .addComponent(shareResultsTitleLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(401, 401, 401))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, resultsPanelLayout.createSequentialGroup()
                        .addComponent(rsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 725, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(129, 129, 129))))
        );
        resultsPanelLayout.setVerticalGroup(
            resultsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resultsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(shareResultsTitleLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rsScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout shareResultsPanelLayout = new javax.swing.GroupLayout(shareResultsPanel);
        shareResultsPanel.setLayout(shareResultsPanelLayout);
        shareResultsPanelLayout.setHorizontalGroup(
            shareResultsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(shareResultsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(resultsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        shareResultsPanelLayout.setVerticalGroup(
            shareResultsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(shareResultsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(resultsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabbedPane.addTab("<html>\n    <font size=\"5\" color=\"#AAAA00\"><b>Share Results</b></font>\n</html>", new javax.swing.ImageIcon(getClass().getResource("/com/inheritance/icons/stats-icon.png")), shareResultsPanel); // NOI18N

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/inheritance/icons/sas_close.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                onCloseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt)
            {
                onCloseHover(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt)
            {
                onCloseExited(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/inheritance/icons/minimize.PNG"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                onMinimizeClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt)
            {
                onMinimizeEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt)
            {
                onMinimizeExited(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/inheritance/icons/allah-icon.png"))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Islamic Inheritance Management System");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout footerPanelLayout = new javax.swing.GroupLayout(footerPanel);
        footerPanel.setLayout(footerPanelLayout);
        footerPanelLayout.setHorizontalGroup(
            footerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        footerPanelLayout.setVerticalGroup(
            footerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout rootPanelLayout = new javax.swing.GroupLayout(rootPanel);
        rootPanel.setLayout(rootPanelLayout);
        rootPanelLayout.setHorizontalGroup(
            rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1071, Short.MAX_VALUE)
            .addComponent(footerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        rootPanelLayout.setVerticalGroup(
            rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rootPanelLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(footerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rootPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rootPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void calculateButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_calculateButtonActionPerformed
    {//GEN-HEADEREND:event_calculateButtonActionPerformed
        if(evt.getSource() == this.calculateButton)
            try
            {
                if(this.deceasedNameTextField.getText().trim().isEmpty())
                {
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(MainFrame.this, "Please enter the deceased's name!", "Error!", JOptionPane.ERROR_MESSAGE);
                }
                else if(!this.maleRadioBtn.isSelected() && !this.femaleRadioBtn.isSelected())
                {
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(MainFrame.this, "Please select the deceased's gender!", "Error!", JOptionPane.ERROR_MESSAGE);
                }
                else if(this.deceasedAgeTextField.getText().trim().isEmpty())
                {
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(MainFrame.this, "Please enter the deceased's age!", "Error!", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    Integer.parseInt(this.deceasedAgeTextField.getText().trim());
                    //Check the assets table
                    if(this.noOfAssetsRows == 0)
                    {
                        Toolkit.getDefaultToolkit().beep();
                        JOptionPane.showMessageDialog(MainFrame.this, "Please enter at least one asset!", "Error!", JOptionPane.ERROR_MESSAGE);
                    }//Check the heirs table
                    else if(this.noOfHeirsRows == 0)
                    {
                        Toolkit.getDefaultToolkit().beep();
                        JOptionPane.showMessageDialog(MainFrame.this, "Please enter at least one heir!", "Error!", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        this.validateDeceasedData();
                        this.validateAssetTable();
                        this.validateHeirsTable();
                        Deceased deceased = this.getDeceased();
                        /**
                         * ****Jed's codes 22/7/16***********
                         */
                        this.addAssetsToDeceased(deceased);
                        this.addHeirsToDeceased(deceased);
                        double totalValueOfAssets = deceased.getTotalAssetValue();
                        double totalValueShared = 0.0;
                        List<Heir> allHeirs = deceased.getHeirs();
                        List<Heir> others = new ArrayList();
                        List<Heir> calculatedHeirs = new ArrayList();
                        double othersPortion = 0.0;
                        //to get no of wives
                        int noOfWives = 0;
                        for(Heir theseHeirs : allHeirs)
                            if(theseHeirs.isWife())
                                ++noOfWives;
                        /*
                         * /end to get no of wives
                         * //to get no Of Daughters
                         * int noOfDaughters = 0;
                         * for(Heir theseHeirs : allHeirs)
                         * if(theseHeirs.isDaughter())
                         * ++noOfDaughters;
                         * //end to get no of Daughters
                         *
                         * //to get no Of Sons
                         * int noOfSons = 0;
                         * for(Heir theseHeirs : allHeirs)
                         * if(theseHeirs.isSon())
                         * ++noOfSons;
                         * //end to get no of Sons
                         *
                         * //to get no Of Granddaughters
                         * int noOfGrandDaughters = 0;
                         * for(Heir theseHeirs : allHeirs)
                         * if(theseHeirs.isGrandDaughter())
                         * ++noOfGrandDaughters;
                         * //end to get no of Granddaughters
                         *
                         * //to get no Of Full Sisters
                         * int noOfFullSisters = 0;
                         * for(Heir theseHeirs : allHeirs)
                         * if(theseHeirs.isFullSister())
                         * ++noOfFullSisters;
                         * //end to get no Of Full Sisters
                         *
                         * //to get no Of Paternal Sisters
                         * int noOfPaternalSisters = 0;
                         * for(Heir theseHeirs : allHeirs)
                         * if(theseHeirs.isPaternalSister())
                         * ++noOfPaternalSisters;
                         * //end to get no Of Paternal Sisters
                         *
                         * //to get no Of Maternal Sibling
                         * int noOfMaternalSibling = 0;
                         * for(Heir theseHeirs : allHeirs)
                         * if(theseHeirs.isMaternalBrother() || theseHeirs.isMaternalSister())
                         * ++noOfMaternalSibling;
                         * //end to get no Of Paternal Sisters
                         *
                         * //to get no Of others
                         */
                        int noOfOthers = 0;
                        /*
                         * for(Heir theseHeirs : theHeirsWe)
                         * if(theseHeirs.isNephew() || theseHeirs.isPaternalNephew() || theseHeirs.isNephewSon() || theseHeirs.isPaternalNephewSon()
                         * || theseHeirs.isUncle() || theseHeirs.isPaternalUncle() || theseHeirs.isCousin() || theseHeirs.isPaternalCousin()
                         * || theseHeirs.isPaternalCousinSon() || theseHeirs.isCousinGrandson() || theseHeirs.isPaternalCousinGrandson()
                         * || theseHeirs.isCousinSon())
                         * ++noOfOthers;
                         * //end to get no Of others
                         */
                        for(Heir heir : allHeirs)
                        {
                            if(heir.isBlockable())
                            {
                                BlockingRules.blockingRuleA(deceased);
                                if(!heir.isBlocked())
                                {
                                    BlockingRules.blockingRuleB(deceased);
                                    if(!heir.isBlocked())
                                    {
                                        BlockingRules.blockingRuleC(deceased);
                                        if(!heir.isBlocked())
                                        {
                                            BlockingRules.blockingRuleD(deceased);
                                            if(!heir.isBlocked())
                                            {
                                                BlockingRules.blockingRuleE(deceased);
                                                if(!heir.isBlocked())
                                                {
                                                    BlockingRules.blockingRuleF(deceased);
                                                    if(!heir.isBlocked())
                                                    {
                                                        BlockingRules.blockingRuleG(deceased);
                                                        if(!heir.isBlocked())
                                                        {
                                                            BlockingRules.blockingRuleH(deceased);
                                                            if(!heir.isBlocked())
                                                            {
                                                                BlockingRules.blockingRuleI(deceased);
                                                                if(!heir.isBlocked())
                                                                {
                                                                    BlockingRules.blockingRuleK(deceased);
                                                                    if(!heir.isBlocked())
                                                                    {
                                                                        BlockingRules.blockingRuleL(deceased);
                                                                        if(!heir.isBlocked())
                                                                        {
                                                                            BlockingRules.blockingRuleM(deceased);
                                                                            if(!heir.isBlocked())
                                                                            {
                                                                                BlockingRules.blockingRuleN(deceased);
                                                                                if(!heir.isBlocked())
                                                                                {
                                                                                    BlockingRules.blockingRuleO(deceased);
                                                                                    if(!heir.isBlocked())
                                                                                    {
                                                                                        BlockingRules.blockingRuleP(deceased);
                                                                                        if(!heir.isBlocked())
                                                                                        {
                                                                                            BlockingRules.blockingRuleQ(deceased);
                                                                                            if(!heir.isBlocked())
                                                                                            {
                                                                                                BlockingRules.blockingRuleR(deceased);
                                                                                                if(!heir.isBlocked())
                                                                                                {
                                                                                                    BlockingRules.blockingRuleS(deceased);
                                                                                                    if(!heir.isBlocked())
                                                                                                    {
                                                                                                        BlockingRules.blockingRuleT(deceased);
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }

                            }
                            if(heir.isBlocked() == false)
                            {
                                if(heir.isHusband())
                                {
                                    double husbandPortion = StandardRules.getHusbandPortion(deceased);
                                    heir.setInheritedValue(husbandPortion);
                                    totalValueShared += husbandPortion;
                                    calculatedHeirs.add(heir);
                                }
                                else if(heir.isWife())
                                {
                                    double wifePortion = StandardRules.getWifePortion(deceased, noOfWives);
                                    heir.setInheritedValue(wifePortion);
                                    totalValueShared += wifePortion;
                                    calculatedHeirs.add(heir);
                                }
                                else if(heir.isGrandSon())
                                {
                                    //double grandSonPortion = StandardRules.getDaughterPortion(deceased, noOfDaughters, noOfSons, noOfWives);
                                    //theseHeirs.setInheritedValue(daughterPortion);
                                }
                                else if(heir.isGrandDaughter())
                                {
                                    double grandDaughterPortion = StandardRules.getGrandDaughterPortion(deceased);
                                    heir.setInheritedValue(grandDaughterPortion);
                                    totalValueShared += grandDaughterPortion;
                                    calculatedHeirs.add(heir);
                                }
                                else if(heir.isFather())
                                {
                                    double fatherPortion = StandardRules.getFatherPortion(deceased);
                                    heir.setInheritedValue(fatherPortion);
                                    totalValueShared += fatherPortion;
                                    calculatedHeirs.add(heir);
                                }
                                else if(heir.isMother())
                                {
                                    double motherPortion = StandardRules.getMotherPortion(deceased);
                                    heir.setInheritedValue(motherPortion);
                                    totalValueShared += motherPortion;
                                    calculatedHeirs.add(heir);
                                }
                                else if(heir.isGrandFather())
                                {
                                    double grandFatherPortion = StandardRules.getPaternalGrandFatherPortion(deceased);
                                    heir.setInheritedValue(grandFatherPortion);
                                    totalValueShared += grandFatherPortion;
                                    calculatedHeirs.add(heir);
                                }
                                else if(heir.isPaternalGrandMother())
                                {
                                    double grandMotherPortion = StandardRules.getPaternalGrandMotherPortion(deceased);
                                    heir.setInheritedValue(grandMotherPortion);
                                    totalValueShared += grandMotherPortion;
                                    calculatedHeirs.add(heir);
                                }
                                else if(heir.isMaternalGrandMother())
                                {
                                    double matGrandMotherPortion = StandardRules.getMaternalGrandMotherPortion(deceased);
                                    heir.setInheritedValue(matGrandMotherPortion);
                                    totalValueShared += matGrandMotherPortion;
                                    calculatedHeirs.add(heir);
                                }
                                else if(heir.isFullBrother())
                                {
                                    //double fullBrotherPortion = StandardRules.getMaternalGrandMotherPortion(deceased);
                                    //theseHeirs.setInheritedValue(fullBrotherPortion);
                                }
                                else if(heir.isFullSister())
                                {
                                    double fullSisterPortion = StandardRules.getFullSisterPortion(deceased);
                                    heir.setInheritedValue(fullSisterPortion);
                                    totalValueShared += fullSisterPortion;
                                    calculatedHeirs.add(heir);
                                }
                                else if(heir.isPaternalBrother())
                                {
                                    //double paternalBrotherPortion = StandardRules.getFullSisterPortion(Deceased deceased, int noOfFullSisters);
                                    //theseHeirs.setInheritedValue(fullSisterPortion);
                                }
                                else if(heir.isPaternalSister())
                                {
                                    double paternalSisterPortion = StandardRules.getPaternalSisterPortion(deceased);
                                    heir.setInheritedValue(paternalSisterPortion);
                                    totalValueShared += paternalSisterPortion;
                                    calculatedHeirs.add(heir);
                                }
                                else if(heir.isMaternalBrother())
                                {
                                    double maternalBrotherPortion = StandardRules.getMaternalSiblingPortion(deceased);
                                    heir.setInheritedValue(maternalBrotherPortion);
                                    totalValueShared += maternalBrotherPortion;
                                    calculatedHeirs.add(heir);
                                }
                                else if(heir.isMaternalSister())
                                {
                                    double maternalSiserPortion = StandardRules.getMaternalSiblingPortion(deceased);
                                    heir.setInheritedValue(maternalSiserPortion);
                                    totalValueShared += maternalSiserPortion;
                                    calculatedHeirs.add(heir);
                                }
                                else if(heir.isNephew() || heir.isPaternalNephew() || heir.isNephewSon() || heir.isPaternalNephewSon()
                                        || heir.isUncle() || heir.isPaternalUncle() || heir.isCousin() || heir.isPaternalCousin()
                                        || heir.isPaternalCousinSon() || heir.isCousinGrandson() || heir.isPaternalCousinGrandson()
                                        || heir.isCousinSon())
                                {
                                    ++noOfOthers;
                                    others.add(heir);
                                    calculatedHeirs.add(heir);
                                }
                                else if(heir.isSon())
                                {
                                    double sonPortion = StandardRules.getSonPortion(deceased);
                                    heir.setInheritedValue(sonPortion);
                                    totalValueShared += sonPortion;
                                    calculatedHeirs.add(heir);
                                }
                                else if(heir.isDaughter())
                                {
                                    double daughterPortion = StandardRules.getDaughterPortion(deceased);
                                    heir.setInheritedValue(daughterPortion);
                                    totalValueShared += daughterPortion;
                                    calculatedHeirs.add(heir);
                                }
                            }
                            else
                            {
                                heir.setInheritedValue(0.00);
                                calculatedHeirs.add(heir);
                            }
                        }

                        if(!others.isEmpty())
                            othersPortion = (totalValueOfAssets - totalValueShared) / noOfOthers;
                        System.out.println("This is the result for  " + deceased.getName());
                        for(Heir spittedHeir : calculatedHeirs)
                            System.out.println(spittedHeir.getName() + " " + spittedHeir.getInheritedValue());
                        ResultsTableModel resultsTableModel = new ResultsTableModel(calculatedHeirs);
                        this.resultsTable.setModel(resultsTableModel);
                        this.tabbedPane.setSelectedIndex(5);
                        /**
                         * ****End of Jed's codes 22/7/16********
                         */
                    }
                }
            }
            catch(IllegalArgumentException | InheritanceException xcp)
            {
                if(xcp instanceof NumberFormatException)
                {
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(MainFrame.this, "Please enter a valid number for the deceased's age!", "Error!", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(MainFrame.this, xcp.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
                }
            }
    }//GEN-LAST:event_calculateButtonActionPerformed

    private void deleteHeirButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_deleteHeirButtonActionPerformed
    {//GEN-HEADEREND:event_deleteHeirButtonActionPerformed
        if(evt.getSource() == this.deleteHeirButton)
            try
            {
                int selectedRow = this.heirsTable.getSelectedRow();
                if(selectedRow == -1)
                    throw new IndexOutOfBoundsException();
                this.heirsTableModel.removeRows(selectedRow);
                this.heirsTableModel.fireTableDataChanged();
                for(int i = 0; i < this.heirsTableModel.getRowCount(); i++)
                    this.heirsTableModel.setValueAt(String.valueOf(i + 1), i, 0);
                --this.noOfHeirsRows;
            }
            catch(IndexOutOfBoundsException xcp)
            {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(this, "Please select a heir to remove!", "Cant Delete!", JOptionPane.WARNING_MESSAGE);
            }
    }//GEN-LAST:event_deleteHeirButtonActionPerformed

    private void newHeirButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_newHeirButtonActionPerformed
    {//GEN-HEADEREND:event_newHeirButtonActionPerformed
        if(evt.getSource() == this.newHeirButton)
        {
            int noOfCols = this.heirsTableModel.getChildColumnNames().length;
            String[] row = new String[noOfCols];
            for(int i = 0; i < row.length; i++)
                if(i == 0)
                    row[i] = String.valueOf(++this.noOfHeirsRows);
                else
                    row[i] = new String();
            this.heirsTableModel.addRow(row);
            this.heirsTableModel.setEditableColumns();
            for(int i = 0; i < this.noOfHeirsRows; i++)
            {
                int heirNameColIndex = this.heirsTableModel.findColumn(HeirsTableModel.HEIR_NAME);
                String name = this.heirsTableModel.getValueAt(i, heirNameColIndex).trim();
                int genderColIndex = this.heirsTableModel.findColumn(HeirsTableModel.GENDER);
                if(!name.isEmpty())
                    this.heirsTableModel.setCellEditable(i, genderColIndex, true);

                String gender = this.heirsTableModel.getValueAt(i, genderColIndex);
                if(gender.equals(Person.MALE) || gender.equals(Person.FEMALE))
                {
                    int heirTypeColIndex = this.heirsTableModel.findColumn(HeirsTableModel.HEIR_TYPE);
                    this.heirsTableModel.setCellEditable(i, heirTypeColIndex, true);
                }
            }
        }
    }//GEN-LAST:event_newHeirButtonActionPerformed

    private void deleteAssetButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_deleteAssetButtonActionPerformed
    {//GEN-HEADEREND:event_deleteAssetButtonActionPerformed
        if(evt.getSource() == this.deleteAssetButton)
            try
            {
                int selectedRow = this.assetsTable.getSelectedRow();
                if(selectedRow == -1)
                    throw new IndexOutOfBoundsException();
                this.assetsTableModel.removeRows(selectedRow);
                this.assetsTableModel.fireTableDataChanged();
                for(int i = 0; i < this.assetsTableModel.getRowCount(); i++)
                    this.assetsTableModel.setValueAt(i + 1, i, 0);
                --this.noOfAssetsRows;
            }
            catch(IndexOutOfBoundsException xcp)
            {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(this, "Please select an asset to remove!", "Cant Delete!", JOptionPane.WARNING_MESSAGE);
            }
    }//GEN-LAST:event_deleteAssetButtonActionPerformed

    private void addAssetButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_addAssetButtonActionPerformed
    {//GEN-HEADEREND:event_addAssetButtonActionPerformed
        if(evt.getSource() == this.addAssetButton)
        {
            int noOfCols;
            noOfCols = this.assetsTableModel.getChildColumnNames().length;
            Object[] row = new Object[noOfCols];
            for(int i = 0; i < row.length; i++)
                if(i == 0)
                    row[i] = ++this.noOfAssetsRows;
                else if(i == 2)
                    row[i] = 0.0;
                else
                    row[i] = new String();
            this.assetsTableModel.addRow(row);
            this.assetsTableModel.setEditableColumns();
            for(int i = 0; i < this.noOfAssetsRows; i++)
            {
                int assetDescColIndex = this.assetsTableModel.findColumn(AssetsTableModel.ASSET_DESC);
                String description = ((String)this.assetsTableModel.getValueAt(i, assetDescColIndex)).trim();
                int assetValueColIndex = this.assetsTableModel.findColumn(AssetsTableModel.ASSET_VALUE);
                if(!description.isEmpty())
                    this.assetsTableModel.setCellEditable(i, assetValueColIndex, true);

                double assetValue = (double)this.assetsTableModel.getValueAt(i, assetValueColIndex);
            }
        }
    }//GEN-LAST:event_addAssetButtonActionPerformed

    private void onCloseHover(java.awt.event.MouseEvent evt)//GEN-FIRST:event_onCloseHover
    {//GEN-HEADEREND:event_onCloseHover
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/inheritance/icons/sas_close_hover.png")));
    }//GEN-LAST:event_onCloseHover

    private void onCloseExited(java.awt.event.MouseEvent evt)//GEN-FIRST:event_onCloseExited
    {//GEN-HEADEREND:event_onCloseExited
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/inheritance/icons/sas_close.png")));
    }//GEN-LAST:event_onCloseExited

    private void onMinimizeEntered(java.awt.event.MouseEvent evt)//GEN-FIRST:event_onMinimizeEntered
    {//GEN-HEADEREND:event_onMinimizeEntered
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/inheritance/icons/minimize2.PNG")));
    }//GEN-LAST:event_onMinimizeEntered

    private void onMinimizeExited(java.awt.event.MouseEvent evt)//GEN-FIRST:event_onMinimizeExited
    {//GEN-HEADEREND:event_onMinimizeExited
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/inheritance/icons/minimize.PNG")));
    }//GEN-LAST:event_onMinimizeExited

    private void onMinimizeClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_onMinimizeClicked
    {//GEN-HEADEREND:event_onMinimizeClicked
        this.setState(Frame.ICONIFIED);
    }//GEN-LAST:event_onMinimizeClicked

    private void mainPanelMouseDragged(java.awt.event.MouseEvent evt)//GEN-FIRST:event_mainPanelMouseDragged
    {//GEN-HEADEREND:event_mainPanelMouseDragged
        this.setLocation(this.getX() + evt.getX() - xPos, this.getY() + evt.getY() - yPos);
    }//GEN-LAST:event_mainPanelMouseDragged

    private void mainPanelPressed(java.awt.event.MouseEvent evt)//GEN-FIRST:event_mainPanelPressed
    {//GEN-HEADEREND:event_mainPanelPressed
        this.xPos = evt.getX();
        this.yPos = evt.getY();
    }//GEN-LAST:event_mainPanelPressed

    private void onCloseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_onCloseClicked
    {//GEN-HEADEREND:event_onCloseClicked
        System.exit(0);
    }//GEN-LAST:event_onCloseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addAssetButton;
    private javax.swing.JScrollPane assetsScrollPane;
    private javax.swing.JTable assetsTable;
    private javax.swing.JLabel assetsTableLabel;
    private javax.swing.JButton calculateButton;
    private javax.swing.JPanel calculatorComponentsPanel;
    private javax.swing.JPanel calculatorPanel;
    private javax.swing.JToolBar calculatorToolBar;
    private javax.swing.JLabel deceasedAgeLabel;
    private javax.swing.JTextField deceasedAgeTextField;
    private javax.swing.JLabel deceasedGenderLabel;
    private javax.swing.JLabel deceasedNameLabel;
    private javax.swing.JTextField deceasedNameTextField;
    private javax.swing.JPanel deceasedPanel;
    private javax.swing.JButton deleteAssetButton;
    private javax.swing.JButton deleteHeirButton;
    private javax.swing.JRadioButton femaleRadioBtn;
    private javax.swing.JPanel footerPanel;
    private javax.swing.ButtonGroup genderButtonGroup;
    private javax.swing.JLabel heirsChartLabel;
    private javax.swing.JPanel heirsChartPanel;
    private javax.swing.JPanel heirsChartSCPanel;
    private javax.swing.JScrollPane heirsChartScrollPane;
    private javax.swing.JScrollPane heirsScrollPane;
    private javax.swing.JTable heirsTable;
    private javax.swing.JLabel heirsTableLabel;
    private javax.swing.JPanel heirsTablePanel;
    private javax.swing.JPanel homePanel;
    private javax.swing.JLabel instrctionsLabel;
    private javax.swing.JPanel instructionPanel;
    private javax.swing.JScrollPane instructionsScrollPane;
    private javax.swing.JTextPane instructionsTextPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton maleRadioBtn;
    private javax.swing.JButton newCaseButton;
    private javax.swing.JButton newHeirButton;
    private javax.swing.JButton openCaseButton;
    private javax.swing.JPanel resultsPanel;
    private javax.swing.JTable resultsTable;
    private javax.swing.JPanel rootPanel;
    private javax.swing.JScrollPane rsScrollPane;
    private javax.swing.JPanel rulesPanel;
    private javax.swing.JScrollPane rulesScrollPane;
    private javax.swing.JTextPane rulesTextPane;
    private javax.swing.JPanel shareResultsPanel;
    private javax.swing.JLabel shareResultsTitleLbl;
    private javax.swing.JPanel standardHeirsPanel;
    private javax.swing.JScrollPane standardHeirsScrollPane;
    private javax.swing.JTextPane standardHeirsTextPane;
    private javax.swing.JLabel subTitleLabel;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JPanel titlePanel;
    // End of variables declaration//GEN-END:variables
}

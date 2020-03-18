package chapter10.telephone;

/**
 * Created by lili on 2017/7/1.
 */
//【例10.1】  电话簿。
//③ TelephoneBookJFrame类实现电话簿的图形用户界面，提供添加、查找和删除功能。

//继承JFrame窗口框架类，实现ListSelectionListener列表框选择事件接口、ActionListener单击事件接口、WindowListener窗口事件接口

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Comparator;
import java.util.Iterator;

public class PhoneBookJFrame extends JFrame
        implements ListSelectionListener, ActionListener, WindowListener
{
    private PhoneBookTreeSet book;                         //电话簿，使用一个树集合存储所有Friend对象
    private JList list;                                    //列表框
    private DefaultListModel listModel;                    //默认列表框模型
    private JTable table;                                  //表格组件
    private DefaultTableModel tableModel;                  //默认表格模型
    private JComboBox combobox_name;                       //姓名组合框
    private DefaultComboBoxModel comboModel;               //组合框模型，选择姓氏
    private JTextField text_code;                          //电话号码文本行

    public PhoneBookJFrame(String filename)                //构造图形用户界面
    {
        super("电话簿");
        this.setBounds(300,300,450,300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.addWindowListener(this);                      //注册窗口事件监听器
        JSplitPane split_h=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT); //水平分割窗格
        split_h.setDividerLocation(40);                    //设置垂直分隔条的位置
        this.getContentPane().add(split_h);
        JSplitPane split_v=new JSplitPane(JSplitPane.VERTICAL_SPLIT); //垂直分割窗格
        split_v.setDividerLocation(180);                   //设置水平分隔条的位置

        this.book = new PhoneBookTreeSet(filename);
        this.listModel = new DefaultListModel();           //默认列表框模型
        this.listModel.addElement("全部");
        this.list = new JList(listModel);                  //创建列表框
        this.list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  //设置单选模式，默认为多选
        this.list.addListSelectionListener(this);          //列表框注册选择事件监听器
        split_h.add(new JScrollPane(this.list));           //添加在滚动窗格中
        split_h.add(split_v);

        String[] columns={"姓名","电话号码"};               //表格各列的中文标题
        this.tableModel=new DefaultTableModel(columns,0);  //默认表格模型，指定列标题，0行
        this.table=new JTable(tableModel);                 //创建空表格，有列标题
        this.list.setSelectedIndex(0);                     //选中列表框第一项，执行valueChanged()方法
        split_v.add(new JScrollPane(table));

        //以下面板包括组合框、文本行和添加、删除、查找等按钮
        JPanel friendpanel=new JPanel(new GridLayout(2,1));
        split_v.add(friendpanel);
        JPanel panels[]={new JPanel(), new JPanel()};
        for (int i=0; i<panels.length; i++)
            friendpanel.add(panels[i]);
        panels[0].add(new JLabel(columns[0]));
        this.comboModel = new DefaultComboBoxModel();      //默认组合框模型
        combobox_name = new JComboBox(this.comboModel);
        combobox_name.setEditable(true);
        panels[0].add(combobox_name);
        panels[0].add(new JLabel(columns[1]));
        this.text_code = new JTextField("12345678901", 12);
        panels[0].add(text_code);
        this.addIndex();                                   //JList添加电话簿中的所有姓氏索引项

        String buttonstr[]={"添加","按姓名查找","按电话号码查找","删除行"};
        JButton buttons[] = new JButton[buttonstr.length];
        for (int i=0; i<buttons.length; i++)
        {
            buttons[i] = new JButton(buttonstr[i]);
            panels[1].add(buttons[i]);
            buttons[i].addActionListener(this);
        }
        this.setVisible(true);
    }

    private void addIndex()                                //JList添加电话簿中所有姓氏索引项
    {
        Iterator<Friend> it=this.book.iterator();          //返回一个迭代器对象
        while (it.hasNext())                               //若有后继元素，使用迭代器遍历集合
        {
            String indexstr=it.next().index();             //获得后继元素姓氏索引项
            if (!this.listModel.contains(indexstr))
            {
                this.listModel.addElement(indexstr);       //列表框模型添加不重复数据项
                this.comboModel.addElement(indexstr);      //组合框模型添加不重复数据项
            }
        }
    }

    public void valueChanged(ListSelectionEvent e)         //列表框的选择事件处理方法
    {
//      System.out.println(" "+selected);  //一次选择，执行两次该事件？？为什么

        String surname=(String)list.getSelectedValue();    //返回列表框选中数据项对象
        if (!this.book.isEmpty() && surname!=null && surname!="")  //以选中姓氏更新表格
        {
            for (int i=this.tableModel.getRowCount()-1; i>=0; i--) //清空表格
                this.tableModel.removeRow(i);
            if (surname=="全部")
                this.addAll();                             //表格添加所有对象
            else                                           //表格添加指定姓氏的所有行
                this.addAll(new Friend(surname, ""), new IndexComparator());
        }
    }
    //将电话簿中满足friend对象和比较器c指定条件的所有对象添加到tableModel表格模型中
    private void addAll(Friend friend, Comparator<Friend> c)
    {
        Iterator<Friend> it = this.book.iterator();
        while (it.hasNext())                           //迭代查找
        {
            Friend f = it.next();
            if (c==null || c.compare(friend, f)==0)    //比较器c指定对象比较规则
                this.tableModel.addRow(f.toArray());   //表格添加一行，参数数组指定各列值
        }
    }
    private void addAll()
    {
        this.addAll(null, null);
    }

    public void actionPerformed(ActionEvent e)             //单击事件处理方法
    {
        String name=(String)combobox_name.getSelectedItem();
        String code=text_code.getText();
        if (e.getActionCommand().equals("添加"))             //单击添加按钮
        {
            Friend f = new Friend(name, code);
            if (!name.equals("") && !this.book.contains(f))//电话簿不能插入姓名空串和重复对象
            {
                this.book.add(f);                          //添加对象，TreeSet不插入重复元素，没提示也不抛出异常
                String surname = f.index();                //返回姓氏
                if (list.getSelectedValue().equals(surname))
                    tableModel.addRow(f.toArray());
                else
                {   if (!listModel.contains(surname))      //列表框中添加不重复元素
                {
                    listModel.addElement(surname);
                    comboModel.addElement(surname);
                }
                    list.setSelectedValue(surname,true);   //设置列表框选中项
                }
            }
            else
                JOptionPane.showMessageDialog(this, "不能添加姓名空串或重复对象"+f.toString());
            return;
        }

        if (!this.book.isEmpty() && e.getActionCommand().endsWith("查找"))
        {                                                  //按姓名或电话号码查找，结果显示在表格中
            for (int i=tableModel.getRowCount()-1; i>=0; i--) //清空表格
                this.tableModel.removeRow(i);
            if (e.getActionCommand().equals("按姓名查找"))
                this.addAll(new Friend(name,""), new Friend(name,""));   //Friend即是比较器对象
                //将电话簿中满足比较器c指定条件的所有对象添加到tableModel表格模型中
            else               //按电话号码查找，返回首次出现的对象，由比较器c指定比较规则
            {   Friend find = this.book.search(new Friend("",code), new CodeComparator());
                if (find!=null)                            //查找成功
                    this.tableModel.addRow(find.toArray());
            }
            return;
        }

        if (e.getActionCommand().equals("删除行"))
        {
            if (this.book.isEmpty())
                JOptionPane.showMessageDialog(this, "表格空，不能删除数据项。");
            else
            {   int i = table.getSelectedRow();            //表格当前选中行号
                if (i==-1)
                    JOptionPane.showMessageDialog(this, "请选择数据项。");
                else
                {   name=(String)table.getValueAt(i,0);
                    int yes=JOptionPane.showConfirmDialog(null, "删除\""+name+"\"行？");//确认对话框包括Yes和No按钮
                    if (yes==0)                            //单击确认对话框的Yes按钮
                    {
                        code=(String)table.getValueAt(i,1);
                        Friend f = new Friend(name, code);
                        this.book.remove(f);               //电话簿中删除对象
                        tableModel.removeRow(i);           //表格中删除一行
                        if (this.book.search(f, new IndexComparator())==null)
                        {                                  //按姓氏查找，电话簿中没有相同姓氏
                            listModel.removeElement(f.index());  //列表框中删除指定姓氏
                            comboModel.removeElement(f.index());
                        }
                    }
                }
            }
        }
    }

    public void windowClosing(WindowEvent e)               //关闭窗口事件处理方法
    {
        this.book.writeToFile();     //将电话簿中所有对象写入指定文件，若文件不存在，创建文件
    }
    public void windowOpened(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}
    public void windowClosed(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}

    public static void main(String args[])
    {
        new PhoneBookJFrame("friends.dat");
    }
}


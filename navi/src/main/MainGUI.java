package main;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import data.DataEvent;
import data.Database;

public class MainGUI {

	DataEvent machineD;

	//�̺�Ʈ ó���� �ʿ��� ����������
	String main_label_text = "";
	Label main_label;
	String location_name;
	int[] location_distance;
	int sequence;
	//boolean isCheck = false; //����߰�,��һ���,��ã����� ��ư�� ��Ȱ��ȭ��Ű������ �� 

	//============ ����ȭ�� ���̺� �ؽ�Ʈ ���� =====================
	void renew_label() {
		main_label_text = "";
		ArrayList<String> tmp = machineD.getName_list();
		main_label_text += "���� ��ϸ� : <"+ machineD.getDb().getName() + ">\n";
		for(int i =0 ; i < tmp.size(); i++) {		
			main_label_text += tmp.get(i) + "       \n";	
		}
		main_label.setText(main_label_text);
	}

	//=============���� ����� ��ư Ŭ�� �̺�Ʈ(�� ��� ����� ��ư Ŭ�� �� ����)==================
	void create_new() {
		Font f = new Font("",Font.PLAIN, 30);
		Frame frame = new Frame("�� ��� �����");

		frame.setBounds(500, 500, 300, 350);
		frame.setBackground(Color.gray);

		Label loc = new Label("��� �̸�");
		loc.setBounds(20, 0, 200, 100);

		TextField text = new TextField(10);
		text.setBounds(20, 200, 200, 70);
		text.setFont(f);

		Button make = new Button("����");
		make.setBounds(20, 300, 80, 40);
		make.setEnabled(false);
		

		Button esc = new Button("���");
		esc.setBounds(150, 300, 80, 40);


		frame.add(text);
		frame.add(make);
		frame.add(esc);
		frame.add(loc);

		frame.setVisible(true);

		//TextField �� ���� �� ��쿡�� �Է¹�ư Ȱ��ȭ
		text.addTextListener(new TextListener() {

			@Override
			public void textValueChanged(TextEvent e) {

				if(text.getText().trim().equals("")) {
					make.setEnabled(false);
				}else {
					make.setEnabled(true);	
				}

			}
		});

		//make(����) ��ư Ŭ��
		make.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
		
				machineD = new DataEvent();
				
				String s = text.getText();
				machineD.add_database(s);
				renew_label();
				
				machineD.setIscheck(1);
				System.out.println(machineD.getIscheck()); //??????????????
				
				frame.setVisible(false);
				
			}
		});
		//x��ư ����
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				frame.setVisible(false);
			}
		});

		//esc��ư ����
		esc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});

	}

	//================== ��Ͽ� ��� �߰� (����߰� ��ư Ŭ�� �� ����)=========================
	void add_location() {
		Font f = new Font("",Font.PLAIN, 30);
		Frame frame = new Frame("����߰�");

		frame.setBounds(500, 500, 300, 350);
		frame.setBackground(Color.gray);

		Label loc = new Label("�߰��� ��Ҹ� �Է� :");
		loc.setBounds(20, 20, 300, 100);

		TextField text = new TextField(10);
		text.setBounds(20, 100, 200, 70);
		text.setFont(f);

		Button make = new Button("�Ϸ�");
		make.setBounds(20, 300, 80, 40);
		make.setEnabled(false);

		Button esc = new Button("���");
		esc.setBounds(150, 300, 80, 40);


		frame.add(text);
		frame.add(make);
		frame.add(esc);
		frame.add(loc);

		frame.setVisible(true);

		sequence = 0;
		location_distance = new int[machineD.getName_list().size()];
		
		//�Ϸ� ��ư Ŭ��
		make.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(sequence == 0) {
					location_name = text.getText();
					if(machineD.getName_list().size() == 0) {
						machineD.add_list(location_name, location_distance);
						renew_label();
						frame.setVisible(false);
					}
					else {
						loc.setText("<" + machineD.getName_list().get(sequence)+" & " +location_name+"> ������ �Ÿ� �Է�");
						sequence++;
					}
				}
				else if(sequence <= machineD.getName_list().size()) {
					location_distance[sequence - 1] = Integer.parseInt(text.getText());
					if(machineD.getName_list().size() == sequence) {
						machineD.add_list(location_name, location_distance);
						renew_label();
						frame.setVisible(false);
					}
					else {
						loc.setText("<" + machineD.getName_list().get(sequence)+" & " +location_name+"> ������ �Ÿ� �Է�");
						sequence++;
					}
				}
			}
		});

		
		//TextField �� ���� �� ��쿡�� �Է¹�ư Ȱ��ȭ
		text.addTextListener(new TextListener() {

			@Override
			public void textValueChanged(TextEvent e) {

				if(text.getText().trim().equals("")) {
					make.setEnabled(false);
				}else {
					make.setEnabled(true);	
				}

			}
		});

		
		//x��ư ����
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				frame.setVisible(false);
			}
		});

		
		//esc��ư ����
		esc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);

			}
		});
	}
 //=================================================================================
	
	
	//================ ��Ͽ��� ��� ����(��һ��� ��ư Ŭ�� �� ����) =============================
	void delete_location() {
		Font f = new Font("",Font.PLAIN, 30);
		Frame frame = new Frame("��һ���");

		frame.setBounds(500, 500, 300, 350);
		frame.setBackground(Color.gray);

		Label loc = new Label("��Ͽ��� ������ ����� �̸��� �Է����ּ���");
		loc.setBounds(20, 0, 200, 100);

		TextField text = new TextField(10);
		text.setBounds(20, 200, 200, 70);
		text.setFont(f);

		Button make = new Button("����");
		make.setBounds(20, 300, 80, 40);
		make.setEnabled(false);

		Button esc = new Button("���");
		esc.setBounds(150, 300, 80, 40);


		frame.add(text);
		frame.add(make);
		frame.add(esc);
		frame.add(loc);

		frame.setVisible(true);

		//TextField �� ���� �� ��쿡�� �Է¹�ư Ȱ��ȭ
		text.addTextListener(new TextListener() {

			@Override
			public void textValueChanged(TextEvent e) {

				if(text.getText().trim().equals("")) {
					make.setEnabled(false);
				}else {
					make.setEnabled(true);	
				}

			}
		});

		//make ��ư Ŭ��
		make.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = text.getText();
				int idx = machineD.getName_list().indexOf(name);
				machineD.delete_list(idx);
				renew_label();
				frame.setVisible(false);
			}
		});
		//x��ư ����
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				frame.setVisible(false);
			}
		});

		//esc��ư ����
		esc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
	}
//============================================================================

	public static void main(String[] args) {	

		boolean bool = false; // ����߰�,��һ���,��ã����� ��ư ��Ȱ��ȭ ��
		
		
		MainGUI m = new MainGUI();
		Frame f = new Frame("�ʱ� ȭ��");
		Font font = new Font("", Font.PLAIN, 18);
		m.machineD = new DataEvent();
		f.setLayout(null);
		f.setBounds(20, 20, 1000, 600);
		f.setBackground(Color.lightGray);
		
		if(m.machineD.getIscheck() == 0) {
			bool = false;
		}else {
			bool = true;
		}
		
		//���̺�
		m.main_label = new Label(m.main_label_text);
		m.main_label.setBounds(100, 100, 800, 400);
		m.main_label.setBackground(Color.gray);
		m.main_label.setFont(font);
		f.add(m.main_label);

		//�ϴܺ� �߰� ��ư
		Panel bottomAdd = new Panel();
		bottomAdd.setLayout(null);
		bottomAdd.setFont(font);
		bottomAdd.setBackground(Color.DARK_GRAY);
		bottomAdd.setBounds(0, 500, 1000, 100);
		int buttonXbase = 75;
		int gan= 180;

		Button create = new Button("�� ��� �����");
		create.setBounds(buttonXbase, 25, 130, 50);
		
		Button load = new Button("�ҷ�����");
		load.setBounds(buttonXbase+gan, 25, 100, 50);

		Button addInfo = new Button("����߰�");
		addInfo.setBounds(buttonXbase+gan*2, 25, 100, 50);
		
		addInfo.setEnabled(bool); //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

		Button deleteInfo = new Button("��һ���");
		deleteInfo.setBounds(buttonXbase+gan*3, 25, 100, 50);
		deleteInfo.setEnabled(bool);

		Button start = new Button("��ã�� ����");
		start.setBounds(buttonXbase+gan*4, 25, 130, 50);
		start.setEnabled(bool);

		//���� ����� ��ư Ŭ��
		create.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("���� �����");
				System.out.println(m.machineD.getIscheck());
				m.create_new();
			}
		});

		//�ҷ����� ��ư Ŭ��
		load.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("�ҷ�����");
			}		
		});

		//����߰� ��ư Ŭ��
		addInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("����߰�");
				m.add_location();
			}
		});

		//��һ��� ��ư Ŭ��
		deleteInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("��һ���");
				m.delete_location();
			}
		});

		//��ã�� ���� ��ư Ŭ��
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("��ã�� ����");
				m.machineD.run_tsp();
			}
		});


		bottomAdd.add(create);
		bottomAdd.add(load);
		bottomAdd.add(addInfo);
		bottomAdd.add(deleteInfo);
		bottomAdd.add(start);
		//�ϴܺ� �߰� ��ư

		//���θ���� �ҷ����� ���� �߰� ��ã�� ����

		f.add(bottomAdd);//�ϴ� �߰� �г�
		f.setVisible(true);

		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});





	}//main
}

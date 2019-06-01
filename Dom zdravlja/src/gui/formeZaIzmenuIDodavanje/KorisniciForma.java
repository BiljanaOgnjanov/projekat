package gui.formeZaIzmenuIDodavanje;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Korisnici.Korisnik;
import Packagemain.DbServis;

import net.miginfocom.swing.MigLayout;

public class KorisniciForma extends JFrame{

	private JLabel lblId = new JLabel("Identifikacioni kod");
	private JTextField txtId = new JTextField(20);
	private JLabel lblIme = new JLabel("Ime");
	private JTextField txtIme = new JTextField(20);
	private JLabel lblPrezime = new JLabel("Prezimea");
	private JTextField txtPrezime = new JTextField(20);
	private JLabel lblPol = new JLabel("Pol");
	private JRadioButton rbtnMuski = new JRadioButton("muski");
	private JRadioButton rbtnZenski = new JRadioButton("zenski");
	private JLabel lblAdresa = new JLabel("Adresa");
	private JTextField txtAdresa = new JTextField(20);
	private JLabel lblJMBG = new JLabel("JMBG");
	private JTextField txtJMBG = new JTextField(20);
	private JLabel lblbrojTelefona = new JLabel("broj telefona");
	private JTextField txtbrojTelefona = new JTextField(20);
	private JLabel lblkorisnickoIme = new JLabel("korisnicko ime");
	private JTextField txtkorisnickoIme = new JTextField(20);
	private JLabel lbllozinka = new JLabel("lozinka");
	private JTextField txtlozinka = new JTextField(20);
	private JLabel lbluloga = new JLabel("uloga");
	private JTextField txtuloga = new JTextField(20);

	
	private DbServis dbservis;
	private Korisnik korisnik;
	
	public KorisniciForma(DbServis dbservis, Korisnik korisnik) {
		this.dbservis = dbservis;
		this.korisnik = korisnik;
		if(korisnik != null) {
			setTitle(korisnik.GetIme() + " - Izmena podataka");
		}else {
			setTitle("Dodavanje artikala");
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		initGUI();
		initActions();
		pack();
	}

	private void initGUI() {
		MigLayout layout = new MigLayout("wrap 2");
		setLayout(layout);
		
		if(korisnik != null) {
			popuniPolja();
		}else {
			rbtnMuski.setSelected(true);
		
		}
		
		add(lblId);
		add(txtId);
		add(lblIme);
		add(txtIme);
		add(lblPrezime);
		add(txtPrezime);
		add(lblPol);
		
		JPanel jPanel = new JPanel();
		
		ButtonGroup tipGroup = new ButtonGroup();
		
		
		tipGroup.add(rbtnMuski);
		tipGroup.add(rbtnZenski);
		
		jPanel.add(rbtnMuski);
		jPanel.add(rbtnZenski);
		add(jPanel);
		add(lblAdresa);
		add(txtAdresa);
		add(lblJMBG);
		add(txtJMBG);
		add(lblbrojTelefona);
		add(txtbrojTelefona);
		add(lblkorisnickoIme);
		add(txtkorisnickoIme);
		add(lbllozinka);
		add(txtlozinka);
		add(lbluloga);
		add(txtuloga);
		
		
	}
	private void popuniPolja() {
		txtId.setText(String.valueOf(korisnik.GetId()));
		txtIme.setText(korisnik.GetIme());
		txtPrezime.setText(korisnik.GetPrezime());
		rbtnMuski.setSelected(korisnik.GetPol());
		txtAdresa.setText(korisnik.GetAdresa());	
		txtJMBG.setText(korisnik.GetJMBG());
		txtbrojTelefona.setText(korisnik.GetBrojTelefona());
		txtkorisnickoIme.setText(korisnik.GetKorisnickoIme());
		txtlozinka.setText(korisnik.GetLozinka());
		txtuloga.setText(String.valueOf(korisnik.GetUloga()));

		
		
	}

	private void initActions() {
		
		
		
}
}
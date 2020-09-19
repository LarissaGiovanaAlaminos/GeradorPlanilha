package br.alaminos.control;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.github.javafaker.Faker;

import br.alaminos.model.Colaborador;
import br.alaminos.utils.Time;

public class CtrlGeraExcel {

	private static final String fileName = "PMEColaboradoresV2";
	private static String result;

	public String create(String _numeroColaboradores, String _localEntrega, String _tipoEntrega) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheetColaboradores = workbook.createSheet("Colaboradores");

		CtrlCPFMaker cpFmaker = new CtrlCPFMaker();
		Faker faker = new Faker(new Locale("FI", "BR"));
		String localEntrega = _localEntrega;

		List<Colaborador> listaColaboradores = new ArrayList<Colaborador>();
		for (int i = 0; i < Integer.parseInt(_numeroColaboradores); i++) {
			listaColaboradores.add(new Colaborador(faker.name().fullName().replace(".", ""), cpFmaker.cpf(false),
					"01/01/1980", 50, _tipoEntrega, localEntrega));
		}
		gerar(workbook, sheetColaboradores, listaColaboradores);

		return result;
	}

	public void gerar(HSSFWorkbook _workbook, HSSFSheet _sheet, List<Colaborador> _listaColaboradores) {

		Row row0 = _sheet.createRow(0);
		Cell cellNome0 = row0.createCell(0);
		cellNome0.setCellValue("NOME DO USUÁRIO");
		Cell cellCPF0 = row0.createCell(1);
		cellCPF0.setCellValue("CPF");
		Cell cellDataNascimento0 = row0.createCell(2);
		cellDataNascimento0.setCellValue("DATA DE NASCIMENTO");
		Cell cellValor0 = row0.createCell(3);
		cellValor0.setCellValue("VALOR");
		Cell cellTipo0 = row0.createCell(4);
		cellTipo0.setCellValue("TIPO");
		Cell cellLocalDeEntrega0 = row0.createCell(5);
		cellLocalDeEntrega0.setCellValue("LOCAL DE ENTREGA");

		int rownum = 1;
		for (Colaborador colaborador : _listaColaboradores) {
			Row row = _sheet.createRow(rownum++);
			int cellnum = 0;
			Cell cellNome = row.createCell(cellnum++);
			cellNome.setCellValue(colaborador.getNome_usuario());
			Cell cellCPF = row.createCell(cellnum++);
			cellCPF.setCellValue(colaborador.getCPF());
			Cell cellDataNascimento = row.createCell(cellnum++);
			cellDataNascimento.setCellValue(colaborador.getData_nascimento());
			Cell cellValor = row.createCell(cellnum++);
			cellValor.setCellValue(colaborador.getValor());
			Cell cellTipo = row.createCell(cellnum++);
			cellTipo.setCellValue(colaborador.getTipo());
			Cell cellLocalDeEntrega = row.createCell(cellnum++);
			cellLocalDeEntrega.setCellValue(colaborador.getLocal_entrega());
		}

		try {
			FileOutputStream out = new FileOutputStream(new File(System.getProperty("user.home") + "/Desktop/"
					+ fileName + "-" + Time.getTime("ddMMyyyy-hhmm") + ".xls"));
			_workbook.write(out);
			out.close();
			result = "Arquivo Excel criado com sucesso!";

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			result = "Arquivo não encontrado!";
			
		} catch (IOException e) {
			e.printStackTrace();
			result = "Erro na edição do arquivo!";
			
		}
	}
}

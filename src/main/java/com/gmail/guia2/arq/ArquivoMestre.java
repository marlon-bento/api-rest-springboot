package com.gmail.guia2.arq;

import com.gmail.guia2.dto.Tab1;
import com.gmail.guia2.dto.Tab2;
import com.gmail.guia2.dto.UsuarioDto;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ArquivoMestre {
	private RandomAccessFile arq;
	public void criarArq() throws IOException {
		arq = new RandomAccessFile("C:\\Users\\marlo\\Documents\\LOGS\\TimeMillis.txt", "rw");
	}
	public void encerrarArq() throws IOException {
		arq.close();
	}
	public void escreveNoArq(String aux) throws Exception {

		criarArq();
		long posicao = arq.length();
		Date data = new Date();
		SimpleDateFormat formatar = new SimpleDateFormat(" dd/MM/yy");
		String dataFormatada = formatar.format(data);
		formatar = new SimpleDateFormat(" H:m");
		String horas = formatar.format(data);
		aux +="       data de modificação:"+dataFormatada+ horas;
		System.out.print("data de modificação:"+dataFormatada+ horas);

		arq.seek(posicao);
		if (posicao >0){
			arq.writeBytes("\n"+aux);
		}
		else {
			arq.writeBytes(aux);
		}
		encerrarArq();
	}


}

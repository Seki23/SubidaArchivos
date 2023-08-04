package com.ues.edu.sv.parcialce1springboot.serviceImpl;

import com.ues.edu.sv.parcialce1springboot.dto.VentasPorCamionyZonaResponseDto;
import com.ues.edu.sv.parcialce1springboot.dto.VentasTotalesResponseDto;
import com.ues.edu.sv.parcialce1springboot.repository.DetalleEntregaRepository;
import com.ues.edu.sv.parcialce1springboot.repository.ZonaRepository;
import com.ues.edu.sv.parcialce1springboot.service.GenerarReportesService;
import com.ues.edu.sv.parcialce1springboot.service.IReporteService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GenerarReporteServiceImpl<T> implements GenerarReportesService<T>{

    private final ZonaRepository zonaRepository;

    private final IReporteService servicePDF;

    private final DetalleEntregaRepository detalleEntregaRepository;


    @Override
    public void generarReporte(HttpServletResponse response) throws IOException {
        final InputStream stream=this.getClass().getResourceAsStream("/reports/VentasPorZonas.jrxml");
        this.servicePDF.generarReporte(stream,response,zonaRepository.findVentasTotales());
    }
///Reporte original si funciona correctamente

    @Override
      public void generateExcel(HttpServletResponse response) throws IOException {
          HSSFWorkbook workbook = new HSSFWorkbook();
          HSSFSheet sheet = workbook.createSheet("VentasPorZona");
          Row filaTitulo= sheet.createRow(1);
          Cell celda= filaTitulo.createCell(1);
          celda.setCellValue("ZONAS");
          sheet.addMergedRegion(new CellRangeAddress(1, 1, 1,  8  ));
  CellUtil.setAlignment(celda, HorizontalAlignment.CENTER);


          Row filaData =sheet.createRow(2);
          int dataRowIndice=1;
          String[] columns={"No","ZONA","TIPO DE TRANSPORTE ","CANTIDAD TRANSPORTE","FECHA","BEBIDAS ALCOHOLICAS","BEBIDAS NO ALCOHOLICAS","TOTAL VENTAS"};
          for (int i = 0; i < columns.length; i++) {
              celda=  filaData.createCell(i);
              celda.setCellValue(columns[i]);
          }
          int dataRowIndex=4;

          List<VentasPorCamionyZonaResponseDto>ventasTotales= this.detalleEntregaRepository.totalVentasPorZonasyCamion();
          int index=1;
          for (VentasPorCamionyZonaResponseDto venta: ventasTotales) {
              filaData= sheet.createRow(dataRowIndex);
              filaData.createCell(0).setCellValue(index);
              filaData.createCell(1).setCellValue(venta.getNombreZona());
              filaData.createCell(2).setCellValue(venta.getTipoVehiculo());
              filaData.createCell(3).setCellValue(venta.getNumVehiculos());
              filaData.createCell(4).setCellValue(venta.getFechaEntrega());
              filaData.createCell(5).setCellValue(venta.getTotalBebidasAlcoholicas());
              filaData.createCell(6).setCellValue(venta.getTotalBebidasNoAlcoholicas());
              filaData.createCell(7).setCellValue(venta.getTotalVenta());
              dataRowIndex++;
              index++;
          }

          ServletOutputStream ops= response.getOutputStream();
          workbook.write(ops);
          workbook.close();
          ops.close();
      }
 

}

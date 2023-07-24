package in.ey.trs.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import in.ey.trs.dto.Report;

public class SubmissionService {

    public static void writeToCsv(Report report) throws IOException {

        File csvOutputFile = new File("C:\\Users\\XP183TY\\Documents\\" + "transaction_report_" + report.getBusinessTransactionId() + ".csv");

        CsvMapper mapper = new CsvMapper();
        mapper.findAndRegisterModules();
        mapper.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        CsvSchema schema = CsvSchema.builder().setUseHeader(true)
                .addColumn("mandate")
                .addColumn("actionType")
                .addColumn("tradeId")
                .addColumn("businessTransactionId")
                .addColumn("productId")
                .addColumn("productIdType")
                .addColumn("quantity")
                .addColumn("cleared")
                .addColumn("deliverableCurrency")
                .addColumn("notional")
                .addColumn("executionTimestamp")
                .addColumn("settlementDate")
                .build();

        ObjectWriter writer = mapper.writerFor(Report.class).with(schema);
        writer.writeValues(csvOutputFile).writeAll(List.of(report));
    }
}
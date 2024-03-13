package com.enviro.assessment.grad001.CliffordKalake.service;

import com.enviro.assessment.grad001.CliffordKalake.model.EnvironmentalData;
import com.enviro.assessment.grad001.CliffordKalake.repository.EnvironmentalDataRepository;
import com.enviro.assessment.grad001.CliffordKalake.util.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileProcessorService {

    @Autowired
    private EnvironmentalDataRepository dataRepository;

    public List<EnvironmentalData> processFile(MultipartFile file) throws FileUploadException {
        if (file.isEmpty()) {
            throw new FileUploadException("File is empty");// Validation for empty input file
        }

        List<EnvironmentalData> dataList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line of the file:
                EnvironmentalData dataEntry = new EnvironmentalData();
                dataEntry.setData(line);// Store the raw line of data
                dataList.add(dataEntry);
            }
        } catch (IOException e) {
            // Handle potential file reading errors
            throw new FileUploadException("Error processing file", e);//Enclose within a custom exception
        }

        //Save all processed data to the database using the repository
        return dataRepository.saveAll(dataList);
    }

}

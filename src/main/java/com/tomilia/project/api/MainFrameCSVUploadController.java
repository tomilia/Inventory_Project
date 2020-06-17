package com.tomilia.project.api;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.tomilia.project.model.Member;
import com.tomilia.project.model.Prod_Loc;
import com.tomilia.project.services.MemberService;
import org.apache.commons.io.input.BOMInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RequestMapping("/")
@Controller
public class MainFrameCSVUploadController {

    private final MemberService memberService;

    @Autowired
    public MainFrameCSVUploadController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public String mainPage(HttpServletRequest request, Model model) {
        model.addAttribute("message", memberService.getAllPeople());
        return "MainPage";
    }

    @PostMapping("/upload-data-detail")
    public String uploadCSVFile(@RequestParam("file") MultipartFile file, Model model) {

        // validate file
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a CSV file to upload.");
            model.addAttribute("status", false);
            return "UploadResult";
        } else {

            try (Reader reader = new BufferedReader(new InputStreamReader(new BOMInputStream(file.getInputStream()), StandardCharsets.UTF_8))) {

                CsvToBean<Member> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(Member.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                List<Member> products = csvToBean.parse();

                // TODO: save inventory in DB?
                model.addAttribute("message", memberService.addProductDetailFromCSV(products));
                model.addAttribute("status", true);

            } catch (Exception ex) {
                model.addAttribute("message", "Error occurred while processing the CSV file. Please try again.");
                model.addAttribute("status", false);
                return "UploadResult";
            }
        }

        return "UploadResult";
    }

    @PostMapping("/upload-data-quantity")
    public String uploadCSVToQuantity(@RequestParam("file") MultipartFile file, Model model) {

        // validate file
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a CSV file to upload.");
            model.addAttribute("status", false);
            return "UploadResult";
        } else {

            try (Reader reader = new BufferedReader(new InputStreamReader(new BOMInputStream(file.getInputStream()), StandardCharsets.UTF_8))) {

                CsvToBean<Prod_Loc> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(Prod_Loc.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                List<Prod_Loc> products = csvToBean.parse();

                // TODO: save inventory in DB?

                model.addAttribute("message", memberService.setProductQuantityFromCSV(products));
                model.addAttribute("status", true);

            } catch (Exception ex) {
                model.addAttribute("message", "Error occurred while processing the CSV file. Please try again.");
                model.addAttribute("status", false);
                return "UploadResult";
            }
        }

        return "UploadResult";
    }

}

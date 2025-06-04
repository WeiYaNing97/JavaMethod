package com.example.test;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateWordDocument {
    public static void main(String[] args) {
        //textGenerationWordFile();
        //GenerateAWordFileBasedOnTheTemplate();
        // 调用方法填充带下划线的Word模板
        WordTemplateFillerWithUnderscores();
    }

    public static void textGenerationWordFile() {
        // 创建一个新的Word文档
        XWPFDocument document = new XWPFDocument();

        // 添加标题（Heading 1）
        XWPFParagraph titleParagraph = document.createParagraph();
        titleParagraph.setStyle("Heading1");
        XWPFRun titleRun = titleParagraph.createRun();
        titleRun.setText("这是一个标题");

        // 添加二级标题（Heading 2）
        XWPFParagraph subTitleParagraph = document.createParagraph();
        subTitleParagraph.setStyle("Heading2");
        XWPFRun subTitleRun = subTitleParagraph.createRun();
        subTitleRun.setText("这是二级标题");

        // 添加二级标题（Heading 3）
        XWPFParagraph subTitleParagraph3 = document.createParagraph();
        subTitleParagraph3.setStyle("Heading3");
        XWPFRun subTitleRun3 = subTitleParagraph3.createRun();
        subTitleRun3.setText("这是3级标题");

        // 第一段落，首行缩进
        XWPFParagraph firstParagraph = document.createParagraph();
        firstParagraph.setIndentationFirstLine(800); // 单位为twips, 1厘米约等于567 twips
        XWPFRun firstRun = firstParagraph.createRun();
        firstRun.setText("这是第1段落的第一行，带有首行缩进。\n接下来是第二行...");

        // 第二段落
        XWPFParagraph secondParagraph = document.createParagraph();
        XWPFRun secondRun = secondParagraph.createRun();
        secondRun.setText("这是第二段落的内容。");

        // 将文档写入文件
        try (FileOutputStream out = new FileOutputStream("D:\\360Downloads\\textGenerationWordFile.docx")) {
            document.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("格式化后的文档已成功创建！");


        System.out.println("文档已成功创建！");
    }

    /**
     * 基于模板生成Word文件 ${} 占位符替换为实际内容
     *
     * <p>该方法基于提供的模板文件路径和输出文件路径，将模板中的占位符替换为指定的参数值，并将生成的Word文件保存到指定路径。
     *
     * @throws IOException 如果在文件读取或写入过程中发生IO异常
     */
    public static void GenerateAWordFileBasedOnTheTemplate() {
        String filePath = "temp\\template.docx"; // 模板文件路径
        String outPath = "D:\\360Downloads\\filled_template.docx"; // 输出文件路径

        try (FileInputStream fis = new FileInputStream(filePath);
             XWPFDocument document = new XWPFDocument(fis)) {

            // 创建一个Map来存放你的参数，键是占位符，值是你想替换的内容
            java.util.Map<String, String> params = new java.util.HashMap<>();
            params.put("${name}", "张三");
            params.put("${date}", "2025-06-04");

            // 遍历文档中的所有段落
            for (XWPFParagraph paragraph : document.getParagraphs()) {
                List<XWPFRun> runs = paragraph.getRuns();
                if (runs != null) {
                    for (XWPFRun run : runs) {
                        String text = run.getText(0);
                        if (text != null && !text.trim().isEmpty()) {
                            // 对文本中的每个参数进行替换
                            for (String key : params.keySet()) {
                                if (text.contains(key)) {
                                    text = text.replace(key, params.get(key));
                                    run.setText(text, 0);
                                }
                            }
                        }
                    }
                }
            }

            // 将修改后的内容写入到新的文档
            try (FileOutputStream fos = new FileOutputStream(outPath)) {
                document.write(fos);
            }

            System.out.println("文档已成功创建！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void WordTemplateFillerWithUnderscores() {
        String filePath = "temp\\template_with_underscores.docx"; // 模板文件路径
        String outPath = "D:\\360Downloads\\filled_template_with_underscores.docx"; // 输出文件路径

        HashMap<String, String> params = new HashMap<>();
        params.put("姓名", "张三");
        params.put("性别", "男");
        params.put("班级", "三年级一班");

        try (FileInputStream fis = new FileInputStream(filePath);
             XWPFDocument document = new XWPFDocument(fis)) {

            // 匹配至少三个连续的下划线
            Pattern pattern = Pattern.compile("(.*?)(_{3,})(.*)");

            for (XWPFParagraph paragraph : document.getParagraphs()) {
                List<XWPFRun> runs = paragraph.getRuns();
                if (runs != null) {
                    for (int i = 0; i < runs.size(); i++) {
                        XWPFRun run = runs.get(i);
                        String text = run.getText(0);
                        if (text != null && !text.trim().isEmpty()) {
                            Matcher matcher = pattern.matcher(text);
                            StringBuffer sb = new StringBuffer();
                            while (matcher.find()) {
                                String prefix = matcher.group(1).trim();
                                String key = prefix.replaceAll("[:：]$", ""); // 提取键名
                                if (params.containsKey(key)) {
                                    // 删除旧run
                                    paragraph.removeRun(i);

                                    // 插入前缀文本
                                    XWPFRun newRunPrefix = paragraph.insertNewRun(i++);
                                    newRunPrefix.setText(prefix);
                                    copyRunProperties(run, newRunPrefix); // 复制格式

                                    // 插入参数值
                                    XWPFRun newRunValue = paragraph.insertNewRun(i++);
                                    newRunValue.setText(params.get(key));
                                    copyRunProperties(run, newRunValue);

                                    // 继续处理剩余部分
                                    matcher.appendReplacement(sb, "");
                                } else {
                                    matcher.appendReplacement(sb, text);
                                }
                            }
                            matcher.appendTail(sb);
                            if (!sb.toString().isEmpty()) { // 如果有未匹配的部分，重新设置
                                boolean isBold = run.isBold();
                                boolean isItalic = run.isItalic();
                                int fontSize = run.getFontSize();
                                String fontFamily = run.getFontFamily();

                                // 插入剩余文本
                                XWPFRun newRunWithRestText = paragraph.insertNewRun(i++);
                                newRunWithRestText.setText(sb.toString());
                                newRunWithRestText.setBold(isBold);
                                newRunWithRestText.setItalic(isItalic);
                                newRunWithRestText.setFontSize(fontSize);
                                newRunWithRestText.setFontFamily(fontFamily);

                                // 移除旧run
                                paragraph.removeRun(i);
                            }
                        }
                    }
                }
            }

            try (FileOutputStream fos = new FileOutputStream(outPath)) {
                document.write(fos);
            }

            System.out.println("文档已成功创建！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void copyRunProperties(XWPFRun source, XWPFRun target) {
        // 这里可以实现复制字体、颜色等属性
        if (source != null) {
            try {
                // 检查run是否有效
                if (source.getCTR() != null) {
                    target.setBold(source.isBold());
                    target.setItalic(source.isItalic());
                    target.setFontSize(source.getFontSize());
                    target.setFontFamily(source.getFontFamily());
                }
            } catch (Exception e) {
                // 如果run无效，使用默认格式
                target.setBold(false);
                target.setItalic(false);
                target.setFontSize(11);
                target.setFontFamily("宋体");
            }
        }
    }

}
package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.model.Course;
import com.example.service.CourseService;

import jakarta.validation.Valid;

@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;

    // 🏠 Trang chủ → index.html
    @GetMapping("/")
    public String viewHomePage(Model model) {
        return findPaginated(1, "courseName", "asc", model);
    }

    // ➕ Form thêm mới → new_course.html
    @GetMapping("/add")
    public String showNewCourseForm(Model model) {
        model.addAttribute("course", new Course());
        return "new_course";
    }

    // 💾 Lưu Course (dùng cho Add + Update)
    @PostMapping("/save")
    public String saveCourse(@Valid @ModelAttribute("course") Course course,
                             BindingResult result,
                             Model model) {
        if (result.hasErrors()) {
            // Nếu có lỗi → quay lại form (tự động map lỗi sang th:errors trong Thymeleaf)
            if (course.getId() != null) {
                return "update_course";
            }
            return "new_course";
        }
        courseService.saveCourse(course);
        return "redirect:/";
    }

    // ✏️ Form cập nhật → update_course.html
    @GetMapping("/update/{id}")
    public String showFormForUpdate(@PathVariable("id") long id, Model model) {
        Course course = courseService.getCourseById(id);
        model.addAttribute("course", course);
        return "update_course";
    }

    // ❌ Xóa Course
    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable("id") long id) {
        courseService.deleteCourseById(id);
        return "redirect:/";
    }

    // 📑 Phân trang + sắp xếp → index.html
    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable("pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<Course> page = courseService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Course> listCourses = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("listCourses", listCourses);

        return "index";
    }
}

package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.TimeTable;
import org.example.exception.DoctorTimetableException;
import org.example.model.TimetableModel;
import org.example.repositrory.TimetableRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/timetable")
public class TimetableController {

    private final TimetableRepository timetableRepository;

    @GetMapping("/count")
    public List<TimeTable> getAllTimeTable() {
        return timetableRepository.findAll();
    }

    @GetMapping("/doctor/{id}")
    public List<TimeTable> getDoctorTimetableById(@PathVariable Long id) {
        return timetableRepository.findAllByDoctorId(id);
    }

    @GetMapping("/patient/{id}")
    public List<TimeTable> getPatientTimetableById(@PathVariable Long id) {
        return timetableRepository.findAllByPatientId(id);
    }

    @PostMapping("/search")
    public List<TimeTable> searchRecord(@RequestBody TimetableModel timetableModel) throws DoctorTimetableException {
        if (!(timetableModel.getDoctorId() == null)) {
            return timetableRepository.findAllByDoctorIdAndLdtBetween(timetableModel.getDoctorId(), timetableModel.getFrom(), timetableModel.getTo());
        }
        throw new DoctorTimetableException();
    }

    @PostMapping("/timetable")
    public TimeTable createRecord(@RequestBody TimetableModel timetableModel) throws DoctorTimetableException {
        TimeTable timeTable = timetableRepository.findFirstByDoctorIdAndLdt(timetableModel.getDoctorId(), timetableModel.getSelectedTime());

        if (!(timeTable == null) && timeTable.isVacant()) {
            timeTable.setPatientId(timetableModel.getPatientId());
            timeTable.setVacant(false);
            return timetableRepository.save(timeTable);
        }

        throw new DoctorTimetableException();
    }

    @PostMapping("/delete")
    public TimeTable deleteRecord(@RequestBody TimetableModel timetableModel) throws DoctorTimetableException {
        TimeTable timeTable = timetableRepository.findFirstByDoctorIdAndLdt(timetableModel.getDoctorId(), timetableModel.getSelectedTime());

        if (!(timeTable == null) ) {
            timeTable.setPatientId(null);
            timeTable.setVacant(true);
            return timetableRepository.save(timeTable);
        }

        throw new DoctorTimetableException();
    }

}

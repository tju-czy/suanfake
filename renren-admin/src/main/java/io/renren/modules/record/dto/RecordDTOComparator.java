package io.renren.modules.record.dto;

import java.util.Comparator;

public class RecordDTOComparator implements Comparator<RecordDTO> {
    @Override
    public int compare(RecordDTO o1, RecordDTO o2){
        return o1.getUploadTime().compareTo(o2.getUploadTime());
    }
}

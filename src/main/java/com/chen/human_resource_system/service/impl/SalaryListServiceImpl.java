package com.chen.human_resource_system.service.impl;

import com.chen.human_resource_system.dao.RecordDao;
import com.chen.human_resource_system.dao.SalaryListDao;

import com.chen.human_resource_system.pojo.SalaryDetails;
import com.chen.human_resource_system.pojo.SalaryList;
import com.chen.human_resource_system.service.SalaryListService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class SalaryListServiceImpl  implements SalaryListService {
    @Resource
    RecordDao recordDao;
    @Resource
    SalaryListDao salaryListDao;
    @Override
    public List<SalaryList> makeAll() {
        List<SalaryList> salaryLists=salaryListDao.findNull();//查出了含不同组合的空机构薪酬发放单

        List<SalaryList> newList=new LinkedList<>();
        int count=salaryListDao.getCount();
        for(SalaryList item:salaryLists){
            //查询改机构组合的所有档案
            List<SalaryDetails> records=recordDao.findNameAndRidByOl(item.getLo1(),item.getLo2(),item.getLo3());
            System.out.println(records);
            count=count+1;
            String id="SG"+ Calendar.getInstance().get(Calendar.YEAR)+String.format("%08d", count);
            item.setSaid(id);
            item.setSalaryDetails(records);
            item.setNumber(records.size());
            double amount = 0d;
            for(SalaryDetails record:records){
                amount=amount+record.getSalaryStandard().getSum();
            }
            item.setAmount(amount);
            newList.add(item);
        }
        return newList;
    }

    @Override
    public List<SalaryList> findAllByTarget(String target) {
        return salaryListDao.findAllByTarget(target);
    }

    @Override
    public void addSalaryList(SalaryList salaryList,String details) {
        salaryList.setTarget("未复核");
        int count=salaryListDao.getCount()+1;
        int count2=salaryListDao.getCount2()+1;
        String id="SG"+ Calendar.getInstance().get(Calendar.YEAR)+String.format("%08d", count);
        salaryList.setSaid(id);
        double realAmount=0;
        List<SalaryDetails> salaryDetails=StringToSalaryDetails(details);
        for(SalaryDetails item:salaryDetails){
            realAmount=realAmount+item.getAmount();
            Long detailId=item.getRid()+ Calendar.getInstance().get(Calendar.YEAR)+count2;
            count2=count2+1;
            item.setRid(detailId);
            item.setSaid(id);
            salaryListDao.insertSalaryDetails(item);
        }
        salaryList.setRealAmount(realAmount);
        salaryListDao.insertSalaryList(salaryList);
    }

    @Override
    public void updateCheck(SalaryList salaryList, String details, String target) {

        List<SalaryDetails> salaryDetails=StringToSalaryDetails(details);
        for(SalaryDetails item:salaryDetails){
            salaryListDao.updateBonus(item.getRid(),item.getBonus(),item.getDeductionBonus(),item.getAmount());
        }
        salaryListDao.updateCheck(salaryList.getSaid(),target,salaryList.getRealAmount());
    }

    @Override
    public List<SalaryList> select(String time, String sid, String key) {
        String sql = "SELECT * FROM salarylist where target='已复核' ";
        if(sid!=null&&sid.length()>0)sql=sql+" and said like '%"+sid+"%'";
        if(key!=null&&key.length()>0)sql=sql+" and (amount like '%"+key+"%' OR  number like '%"+key+"%' OR  registrant like '%"+key+"%') ";
        if (time != null && time.contains(",")) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date start = new Date(time.split(",")[0]);
            Date end = new Date(time.split(",")[1]);
            sql = sql + " and registrationTime BETWEEN '" + sdf.format(start) + "' and '" + sdf.format(end)+"'";
        }
        return salaryListDao.select(sql);
    }


    private List<SalaryDetails> StringToSalaryDetails(String details){
        List<SalaryDetails> list=new LinkedList<>();
        String []items=details.split("@chen");

        for(int i=1;i<items.length;i++){
            SalaryDetails sd=new SalaryDetails();
            String []filed=items[i].split("#chen");
            sd.setRid(Long.parseLong(filed[0]));
            sd.setUsername(filed[1]);
            sd.setBonus(Double.parseDouble(filed[2]));
            sd.setDeductionBonus(Double.parseDouble(filed[3]));
            sd.setSaid(filed[4]);
            sd.setSalary_standard(Long.parseLong(filed[5]));
            sd.setAmount(Double.parseDouble(filed[6]));
            list.add(sd);
        }
        return list;
    }
}

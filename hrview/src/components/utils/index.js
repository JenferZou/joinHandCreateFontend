//按学号删除学生
export function deleteOneStudent(student,studentData) {
    for(let i=0; i<studentData.length; i++){
        if(student.sno===studentData[i].sno){
            studentData.splice(i,1)
        }
    }
    return studentData;
}



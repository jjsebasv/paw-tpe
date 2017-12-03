package ar.edu.itba.paw.dtos;

import ar.edu.itba.paw.models.Client;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RecoveryQuestionDTO {

    private String recoveryQuestion;

    public RecoveryQuestionDTO() {

    }

    public RecoveryQuestionDTO(Client client) {
        this.recoveryQuestion = client.getRecoveryQuestion();
    }

    public String getRecoveryQuestion() {
        return recoveryQuestion;
    }

    public void setRecoveryQuestion(String recoveryQuestion) {
        this.recoveryQuestion = recoveryQuestion;
    }
}

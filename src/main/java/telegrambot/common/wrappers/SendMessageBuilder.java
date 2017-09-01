package telegrambot.common.wrappers;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.exceptions.TelegramApiValidationException;

/**
 * Created by atols on 30.08.2017.
 */
public class SendMessageBuilder extends SendMessage {

    public void send(){

    }

    public SendMessageBuilder() {
        super();
    }

    public SendMessageBuilder(String chatId, String text) {
        super(chatId, text);
    }

    public SendMessageBuilder(Long chatId, String text) {
        super(chatId, text);
    }

    @Override
    public String getChatId() {
        return super.getChatId();
    }

    @Override
    public SendMessage setChatId(String chatId) {
        return super.setChatId(chatId);
    }

    @Override
    public SendMessage setChatId(Long chatId) {
        return super.setChatId(chatId);
    }

    @Override
    public String getText() {
        return super.getText();
    }

    @Override
    public SendMessage setText(String text) {
        return super.setText(text);
    }

    @Override
    public Integer getReplyToMessageId() {
        return super.getReplyToMessageId();
    }

    @Override
    public SendMessage setReplyToMessageId(Integer replyToMessageId) {
        return super.setReplyToMessageId(replyToMessageId);
    }

    @Override
    public ReplyKeyboard getReplyMarkup() {
        return super.getReplyMarkup();
    }

    @Override
    public SendMessage setReplyMarkup(ReplyKeyboard replyMarkup) {
        return super.setReplyMarkup(replyMarkup);
    }

    @Override
    public Boolean getDisableWebPagePreview() {
        return super.getDisableWebPagePreview();
    }

    @Override
    public Boolean getDisableNotification() {
        return super.getDisableNotification();
    }

    @Override
    public SendMessage disableWebPagePreview() {
        return super.disableWebPagePreview();
    }

    @Override
    public SendMessage enableWebPagePreview() {
        return super.enableWebPagePreview();
    }

    @Override
    public SendMessage enableNotification() {
        return super.enableNotification();
    }

    @Override
    public SendMessage disableNotification() {
        return super.disableNotification();
    }

    @Override
    public SendMessage setParseMode(String parseMode) {
        return super.setParseMode(parseMode);
    }

    @Override
    public SendMessage enableMarkdown(boolean enable) {
        return super.enableMarkdown(enable);
    }

    @Override
    public SendMessage enableHtml(boolean enable) {
        return super.enableHtml(enable);
    }

    @Override
    public String getMethod() {
        return super.getMethod();
    }

    @Override
    public Message deserializeResponse(String answer) throws TelegramApiRequestException {
        return super.deserializeResponse(answer);
    }

    @Override
    public void validate() throws TelegramApiValidationException {
        super.validate();
    }
}

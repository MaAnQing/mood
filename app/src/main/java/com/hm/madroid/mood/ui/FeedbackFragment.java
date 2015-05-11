package com.hm.madroid.mood.ui;


import android.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hm.madroid.mood.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author madroid
 *
 */
public class FeedbackFragment extends Fragment implements View.OnClickListener{
    private EditText mContent;
    private EditText mEmail;
    private View mSend;
    private TextView homeBack ;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_feedback, container, false);

        homeBack = (TextView)v.findViewById(R.id.home_back);
        homeBack.setText(getString(R.string.title_feedback));
        homeBack.setOnClickListener(this);

        mContent = (EditText) v.findViewById(R.id.feedback_content);
        mEmail = (EditText) v.findViewById(R.id.feedback_email);
        mSend = v.findViewById(R.id.feedback);

        mSend.setOnClickListener(this);
        mEmail.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (EditorInfo.IME_ACTION_DONE == actionId) {
                    onClick(mSend);
                    return true;
                }

                return false;
            }
        });

        return v;
    }

    private void sendMsg(){
        String content = mContent.getText().toString();
        String email = mEmail.getText().toString();

        if (isContentValid(content)) {
            if (isEmailValid(email)) {
                sendFeedback(content, email);

                //UmengAnalytics.event(getActivity(), UmengAnalytics.EVENT_ABOUT_SEND_FEEDBACK);
            } else {
                Toast.makeText(getActivity(), R.string.feedback_invalid, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getActivity(), R.string.feedback_empty, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.home_back:
                getActivity().finish();
                break;
            case R.id.feedback:
                sendMsg();
                break;
            default:
                break;
        }

    }

    public static boolean isContentValid(String content) {
        if (!TextUtils.isEmpty(content)) {
            return true;
        }

        return false;
    }

    private static final String EMAIL_REGEX = "^[\\w\\-\\.]+@[\\w\\-]+(\\.\\w+)+$";
    private static final String NUM_REGEX = "^\\d+$";

    public static boolean isEmailValid(String email) {
        if (!TextUtils.isEmpty(email)) {
            Matcher matcher = Pattern.compile(EMAIL_REGEX).matcher(email);
            if (matcher.matches())
                return true;
            else {
                matcher = Pattern.compile(NUM_REGEX).matcher(email);
                if (matcher.matches())
                    return true;
            }
        }

        return false;
    }

    private void sendFeedback(String content, String email) {

        //TODO 编写发送邮件方法
//        WebAPI.sendFeedback(LoginKeeper.readLoginData(getActivity()), content, email, new AsyncHttpResponseHandler() {
//            @Override
//            public void onStart() {
//                mSend.setEnabled(false);
//            }
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//                String content = Utils.getStringFromBytes(responseBody);
//                Debug.i("DDDD", "Send Feedback : " + statusCode + ",content : " + content);
//
//                WebResponse ws = WebResponse.parse(getActivity(), content);
//                if (ws.success()) {
//                    CustomToast.makeText(BraceletApp.getContext(), R.string.feedback_ok, Toast.LENGTH_SHORT).show();
//                    if (getActivity() != null) {
//                        getActivity().finish();
//                    }
//                } else {
//                    CustomToast.makeText(BraceletApp.getContext(), R.string.feedback_failed, Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//                CustomToast.makeText(getActivity(), R.string.feedback_failed, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFinish() {
//                mSend.setEnabled(true);
//            }
//        });
    }
}

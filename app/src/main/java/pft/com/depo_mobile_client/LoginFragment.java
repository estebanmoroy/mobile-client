package pft.com.depo_mobile_client;

import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import pft.com.depo_mobile_client.familias.FamiliasFragment;
import pft.com.depo_mobile_client.productos.ProductosListaFragment;

public class LoginFragment extends Fragment {

  @Override
  public View onCreateView(
          @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.login_fragment, container, false);

    final TextInputLayout contrasenaTextInput = view.findViewById(R.id.password_text_input);
    final TextInputEditText contrasenaEditText = view.findViewById(R.id.password_edit_text);
    MaterialButton loginButton = view.findViewById(R.id.login_button);

    //Login Button onClick Action
    loginButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view){
        if (!isPasswordValid(contrasenaEditText.getText())){
          contrasenaTextInput.setError(getString(R.string.depo_error_login));
        }else{
          contrasenaTextInput.setError(null);
          //Navegar al siguiente Fragment
          ((NavigationHost) getActivity()).navigateTo(new FamiliasFragment(), false);
        }
      }
    });



    // Snippet from "Navigate to the next Fragment" section goes here.

    return view;
  }

  private boolean isPasswordValid(@Nullable Editable text){
    return text != null && text.length() >= 8;
  }
}


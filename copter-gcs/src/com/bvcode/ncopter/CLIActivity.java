package com.bvcode.ncopter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.MAVLink.Messages.IMAVLinkMessage;
import com.bvcode.ncopter.AC1Data.ProtocolParser;
import com.bvcode.ncopter.AC1Data.RawByte;
import com.bvcode.ncopter.comms.CommunicationClient;

public class CLIActivity extends Activity implements OnClickListener {
	
	EditText buffer;
	EditText out;
	Button send;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.cli_view);
		buffer = (EditText) findViewById(R.id.cli_text);
		out = (EditText) findViewById(R.id.cli_to_send);
		send = (Button) findViewById(R.id.cli_send);
		send.setOnClickListener(this);
		
		ProtocolParser.setPassThrough(true);
        ba.init();
        
        
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		ba.onDestroy();
	
		ProtocolParser.setPassThrough(false);
		
	}
	
	CommunicationClient ba = new CommunicationClient(this) {

		@Override
		public void notifyConnected() {
			String toSend = "\n";
			ba.sendBytesToComm(toSend.getBytes());
		      
			
		}

		@Override
		public void notifyDisconnected() {
			
		}

		@Override
		public void notifyDeviceNotAvailable() {
			
		}

		@Override
		public void notifyReceivedData(int count, IMAVLinkMessage m) {
			if( RawByte.class.isInstance(m)){
				RawByte raw = (RawByte)m;
				String s = buffer.getText().toString();
				buffer.setText(s + (char)raw.b);
						
				
			}
		}
		
		
	};

	@Override
	public void onClick(View v) {
		if( v == send){
			ba.sendBytesToComm(out.getText().toString().getBytes());
			
		}
		
	}
}

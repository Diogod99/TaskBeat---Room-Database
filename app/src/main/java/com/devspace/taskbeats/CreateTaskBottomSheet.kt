package com.devspace.taskbeats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText

class CreateTaskBottomSheet(
    private val categoryList: List<CategoryUiData>,
    private val onCreateClicked: (TaskUiData) -> Unit
    ) : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewTask = inflater.inflate(R.layout.create_task_bottom_sheet, container, false)

        val btnCreateTask = viewTask.findViewById<Button>(R.id.btn_task_create)
        val tieTaskName = viewTask.findViewById<TextInputEditText>(R.id.tie_task_name)

        var taskCategory: String? = null

        btnCreateTask.setOnClickListener {
            val taskName = tieTaskName.text.toString()
            if (taskCategory != null) {
                onCreateClicked.invoke(
                    TaskUiData(
                        name = taskName,
                        category = taskCategory!!
                    )
                )
                dismiss()
            } else {
                Snackbar.make(btnCreateTask, "Please select a category", Snackbar.LENGTH_LONG)
                    .show()
            }

        }

        val categoryStrs = categoryList.map { it.name }

        val spinner = viewTask.findViewById<Spinner>(R.id.spi_category_list)
        ArrayAdapter(
            requireActivity().baseContext,
            android.R.layout.simple_spinner_item,
            categoryStrs
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                taskCategory = categoryStrs[p2]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }

        return viewTask
    }
}
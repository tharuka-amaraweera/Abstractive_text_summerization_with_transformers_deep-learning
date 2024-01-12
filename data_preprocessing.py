import pandas as pd
import numpy as np
import tensorflow as tf
import time
import re
import pickle

# create tf dataset by prepocessing the raw data

def data_preprocess(document, summary):
  # input: document and summary as list of texts of pandas series
  # output tensorflow dataset
  
  # Data preprocessing
  # for decoder sequence
  summary = summary.apply(lambda x: '<go> ' + x + ' <stop>')

  # Tokenization the text into integer tokens
  # Pad the genining and end of each sentnces with (“<go>”) and end (“<stop>”) tokens.
  filters = '!"#$%&()*+,-./:;=?@[\\]^_`{|}~\t\n'
  oov_token = '<unk>'

  # create tockenizer
  document_tokenizer = tf.keras.preprocessing.text.Tokenizer(oov_token=oov_token)
  summary_tokenizer = tf.keras.preprocessing.text.Tokenizer(filters=filters, oov_token=oov_token)

  # fit a Tokenizer on the sequences to filter punctuation marks, convert text to lower case, maintain a vocabulary dict (using a mapping of words with their token equivalents)
  document_tokenizer.fit_on_texts(document)
  summary_tokenizer.fit_on_texts(summary)

  # converts the textual data to tokens which can be directly inputted to the model

  inputs = document_tokenizer.texts_to_sequences(document)
  targets = summary_tokenizer.texts_to_sequences(summary)

  encoder_vocab_size = len(document_tokenizer.word_index) + 1
  decoder_vocab_size = len(summary_tokenizer.word_index) + 1

  # vocab_size
  print(encoder_vocab_size, decoder_vocab_size)

  # Getting insights on lengths for defining maximum length
  document_lengths = pd.Series([len(x) for x in document])
  summary_lengths = pd.Series([len(x) for x in summary])

  # Checking lengths and information
  print(document_lengths.describe())
  print(summary_lengths.describe())

  # maxlength
  # taking values > and round figured to 75th percentile at the same time not leaving high variance
  encoder_maxlen = 400
  decoder_maxlen = 75

  # Padding/Truncating sequences for identical sequence lengths
  inputs = tf.keras.preprocessing.sequence.pad_sequences(inputs, maxlen=encoder_maxlen, padding='post', truncating='post')
  targets = tf.keras.preprocessing.sequence.pad_sequences(targets, maxlen=decoder_maxlen, padding='post',
                                                          truncating='post')

  # Cerating data pipeline
  inputs = tf.cast(inputs, dtype=tf.int32)
  targets = tf.cast(targets, dtype=tf.int32)

  BUFFER_SIZE = 20000
  BATCH_SIZE = 64

  dataset = tf.data.Dataset.from_tensor_slices((inputs, targets)).shuffle(BUFFER_SIZE).batch(BATCH_SIZE)
  
  return dataset


if __name__ == '__main__':
  
  # Load data
  news = pd.read_excel("data/news.xlsx")
  news.drop(['Source ', 'Time ', 'Publish Date'], axis=1, inplace=True)
  # View raw data
  print(news.head())

  # Define docs and summaries
  document = news['news']
  summary = news['Headline']
  
  # get dataset
  dataset = data _preprocess(document, summary)

